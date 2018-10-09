package com.zwb.ums.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import javax.servlet.http.HttpSession;
import javax.websocket.*;
import javax.websocket.server.ServerEndpoint;
import java.io.IOException;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentMap;
import java.util.concurrent.CopyOnWriteArrayList;
import java.util.concurrent.CopyOnWriteArraySet;

/**
 * <p>
 * Title: WebSocket
 * </p>
 * <p>
 * Description: WebSocket消息处理类
 * </p>
 * <p>
 * Company: 都市放牛集团
 * </p>
 *
 * @author zhouwenbo
 * @version 1.0
 * @date 2018/5/5 23:09
 */

/**
 * @ServerEndpoint("/webSocket") 声明websocket的连接路径
 */
@Component
@ServerEndpoint("/webSocket")
//@Slf4j
public class WebSocket {

    private Logger logger = LoggerFactory.getLogger(WebSocket.class);

    /** 建立websocket连接的数量 */
    private static int onlineCount = 0;

    /** 用户建立连接的唯一会话,用来区别每一个用户 */
    private Session session;

    private static CopyOnWriteArraySet<WebSocket> webSocketSet = new CopyOnWriteArraySet<>();

    /** 存放所有登录用户的Map集合, 键:用户的唯一标识 **/
    private static ConcurrentMap<String,WebSocket> userSocketMap = new ConcurrentHashMap<String, WebSocket>();

    /**httpsession用以在建立连接的时候获取登陆用户的唯一标识，获取后以键值对的形式存放在Map对象中 */
    private static HttpSession httpSession;

    public static HttpSession getHttpSession() {
        return httpSession;
    }

    public static void setHttpSession(HttpSession httpSession) {
        WebSocket.httpSession = httpSession;
    }

    /**
     * 建立连接成功回调的方法
     * @param session session为与某个客户端的连接会话，需要通过它来给客户端发送数据
     */
    @OnOpen
    public void onOpen(Session session){
        this.session = session;
        webSocketSet.add(this);
        logger.info("[webSocket消息] 你有新的连接，当前连接总数:{ "+webSocketSet.size()+" }");
    }

    /**
     * 断开连接
     */
    @OnClose
    public void onClose(){
        webSocketSet.remove(this);
        logger.info("[webSocket消息] 连接断开,当前连接总数:{ "+webSocketSet.size()+" }");
    }

    /**
     * @Title: onMessage
     * @Description: 收到客户端消息后调用的方法
     * @param: message 客户端发送过来的消息
     * @return: 
     * @author zhouwenbo
     * @date 2018/5/7 10:19
     * @version 1.0
     */
    @OnMessage
    public void onMessage(String message){
        logger.info("[webSocket消息] 收到客户端发来的消息:{ "+message+" }");
    }

    /**
     * @Title: sendMessage
     * @Description: 服务端向客户端广播消息
     * @param: message 广播的消息
     * @return: 
     * @author zhouwenbo
     * @date 2018/5/7 10:21
     * @version 1.0
     */
    public void sendMessage(String message) {
        for (WebSocket webSocket:webSocketSet) {
            logger.info("[webSocket消息] 广播消息, message={ "+message+" }");
            try {
                webSocket.session.getBasicRemote().sendText(message);
            } catch (IOException e) {
                e.printStackTrace();
            }

        }
    }

    /**
     * 向所有在线用户发送在线人数
     * @param message
     */
    public void sendOnlineCount(String message) {
        for (Map.Entry<String,WebSocket> entry : userSocketMap.entrySet()) {
            logger.info("[webSocket消息] 广播消息, message={ "+message+" }");
            entry.getValue().sendMessage(message);
        }
    }

    /**
     * @Title: onError
     * @Description: websocket连接错误
     * @param: session
     * @param: error
     * @return:
     * @author zhouwenbo
     * @date 2018/5/7 10:23
     * @version 1.0
     */
    @OnError
    public void onError(Session session, Throwable error){
        error.printStackTrace();
    }

}
