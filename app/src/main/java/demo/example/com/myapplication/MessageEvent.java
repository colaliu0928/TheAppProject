package demo.example.com.myapplication;

/**
 * 类的作用：消息事件类
 * lenovo 刘珂珂
 * 2018/7/21
 * 16:11
 */

public class MessageEvent {

    private String message;

    public MessageEvent(String message) {
        this.message = message;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    @Override
    public String toString() {
        return "MessageEvent{" +
                "message='" + message + '\'' +
                '}';
    }
}
