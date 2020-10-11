package sw.chicha.Member.service;

import net.nurigo.java_sdk.api.Message;
import net.nurigo.java_sdk.Coolsms;
import net.nurigo.java_sdk.exceptions.CoolsmsException;
import org.json.simple.JSONObject;
import org.springframework.stereotype.Service;

import java.util.HashMap;

@Service
public class CertificationService {
    
    public void certifiedPhoneNumber(String phoneNumber, String cerNum) {
        String api_key = "NCSXY2QQLMDKPBJ1";
        String api_secret = "3BDH9I5ZAEGI40KWIXMYDFZKXETY8XDR";
        Message coolsms = new Message(api_key, api_secret);

        HashMap<String, String> params = new HashMap<String, String>();
        params.put("to", phoneNumber);  // 수신 전화번호
        params.put("from", "발신번호입력");
        params.put("type", "SMS");
        params.put("text", "테스트 : 인증번호는" + "["+cerNum+"]"+"입니다.");
        params.put("app_version", "test app 1.2"); // application name and version

        try {
            JSONObject obj = (JSONObject) coolsms.send(params);
            System.out.println("to"+ obj.toString());
            System.out.println("from" + obj.toString());
        } catch (CoolsmsException e) {
            System.out.println(e.getMessage());
            System.out.println(e.getCode());
        }
    }
}
