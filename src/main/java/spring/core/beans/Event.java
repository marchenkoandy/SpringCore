package spring.core.beans;

import java.util.Date;
import java.util.Random;

public class Event {
    private int id = new Random().nextInt();
    private String msg;
    private Date date;

    public Event(){}
//    public Event(Date date){
//        this.date = date;
//    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder("Event{");
        sb.append("id=").append(id);
        sb.append(", msg='").append(msg).append('\'');
        sb.append(", date=").append(date);
        sb.append('}');
        return sb.toString();
    }
}
