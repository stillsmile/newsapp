package bean;

/**
 * Created by lenovo on 2018/5/28.
 */

public class BusDetailInfoBean {

    public String ID;//站台的id
    public String BusInfo;//车牌
    public String Code;//
    public String InTime;//进站时间
    public String OutTime;//出站时间
    public String StationCName;//站台名称

    public BusDetailInfoBean() {
    }
    public BusDetailInfoBean(String ID, String busInfo, String code, String inTime, String outTime, String stationCName) {
        this.ID = ID;
        BusInfo = busInfo;
        Code = code;
        InTime = inTime;
        OutTime = outTime;
        StationCName = stationCName;
    }
}
