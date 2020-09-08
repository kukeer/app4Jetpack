package com.ws.hugs.common.utils;

import android.Manifest;
import android.content.Context;
import android.content.pm.PackageManager;
import android.telephony.TelephonyManager;

import androidx.core.app.ActivityCompat;

/**
 * 读取手机设备信息测试代码
 */
public class PhoneInfo {

//    private TelephonyManager telephonyManager;
//    /**
//     * 国际移动用户识别码
//     */
//    private String IMSI;
//    private Context cxt;
//
//    public PhoneInfo(Context context) {
//        cxt = context;
//        telephonyManager = (TelephonyManager) context
//                .getSystemService(Context.TELEPHONY_SERVICE);
//    }
//
//    /**
//     * 获取电话号码
//     */
//    public String getNativePhoneNumber() {
//        String NativePhoneNumber = null;
//
//        if (ActivityCompat.checkSelfPermission(cxt, Manifest.permission.READ_SMS) != PackageManager.PERMISSION_GRANTED && ActivityCompat.checkSelfPermission(cxt, Manifest.permission.READ_PHONE_NUMBERS) != PackageManager.PERMISSION_GRANTED && ActivityCompat.checkSelfPermission(cxt, Manifest.permission.READ_PHONE_STATE) != PackageManager.PERMISSION_GRANTED) {
//            // TODO: Consider calling
//            //    ActivityCompat#requestPermissions
//            // here to request the missing permissions, and then overriding
//            //   public void onRequestPermissionsResult(int requestCode, String[] permissions,
//            //                                          int[] grantResults)
//            // to handle the case where the user grants the permission. See the documentation
//            // for ActivityCompat#requestPermissions for more details.
//            NativePhoneNumber = telephonyManager.getLine1Number();
//            return NativePhoneNumber;
//        }
//        NativePhoneNumber = telephonyManager.getLine1Number();
//        if (NativePhoneNumber.startsWith("+86")){
//            NativePhoneNumber = NativePhoneNumber.replace("+86","");
//        }else{
//            return "0";
//        }
//        return NativePhoneNumber;
//    }
//
//    /**
//     * 获取手机服务商信息
//     */
//    public String getProvidersName() {
//        String ProvidersName = "N/A";
//        try {
//            if (ActivityCompat.checkSelfPermission(cxt, Manifest.permission.READ_PHONE_STATE) != PackageManager.PERMISSION_GRANTED) {
//                // TODO: Consider calling
//                //    ActivityCompat#requestPermissions
//                // here to request the missing permissions, and then overriding
//                //   public void onRequestPermissionsResult(int requestCode, String[] permissions,
//                //                                          int[] grantResults)
//                // to handle the case where the user grants the permission. See the documentation
//                // for ActivityCompat#requestPermissions for more details.
//                return "TODO";
//            }
//            IMSI = telephonyManager.getSubscriberId();
//            // IMSI号前面3位460是国家，紧接着后面2位00 02是中国移动，01是中国联通，03是中国电信。
//            System.out.println(IMSI);
//            if (IMSI.startsWith("46000") || IMSI.startsWith("46002")) {
//                ProvidersName = "中国移动";
//            } else if (IMSI.startsWith("46001")) {
//                ProvidersName = "中国联通";
//            } else if (IMSI.startsWith("46003")) {
//                ProvidersName = "中国电信";
//            }
//        }catch(Exception e){
//            e.printStackTrace();
//        }
//        return ProvidersName;
//    }
//
//    public String  getPhoneInfo(){
//        TelephonyManager tm = (TelephonyManager)cxt.getSystemService(Context.TELEPHONY_SERVICE);
//        StringBuilder sb = new StringBuilder();
//
////        sb.append("\nDeviceId(IMEI) = " + tm.getDeviceId());
////        sb.append("\nDeviceSoftwareVersion = " + tm.getDeviceSoftwareVersion());
////        sb.append("\nLine1Number = " + tm.getLine1Number());
//        sb.append("\nNetworkCountryIso = " + tm.getNetworkCountryIso());
//        sb.append("\nNetworkOperator = " + tm.getNetworkOperator());
//        sb.append("\nNetworkOperatorName = " + tm.getNetworkOperatorName());
//        sb.append("\nNetworkType = " + tm.getNetworkType());
//        sb.append("\nPhoneType = " + tm.getPhoneType());
//        sb.append("\nSimCountryIso = " + tm.getSimCountryIso());
//        sb.append("\nSimOperator = " + tm.getSimOperator());
//        sb.append("\nSimOperatorName = " + tm.getSimOperatorName());
////        sb.append("\nSimSerialNumber = " + tm.getSimSerialNumber());
//        sb.append("\nSimState = " + tm.getSimState());
////        sb.append("\nSubscriberId(IMSI) = " + tm.getSubscriberId());
////        sb.append("\nVoiceMailNumber = " + tm.getVoiceMailNumber());
//        return  sb.toString();
//    }
}