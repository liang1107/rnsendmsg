package com.rnsendmsg;

import android.app.Activity;
import android.content.Intent;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Build;
import android.telephony.SmsManager;
import android.text.TextUtils;
import android.util.Log;
import android.view.inputmethod.InputMethodManager;
import android.widget.Toast;


import com.facebook.react.bridge.Callback;
import com.facebook.react.bridge.ReactApplicationContext;
import com.facebook.react.bridge.ReactContext;
import com.facebook.react.bridge.ReactContextBaseJavaModule;
import com.facebook.react.bridge.ReactMethod;
import com.facebook.react.modules.core.DeviceEventManagerModule;


import org.json.JSONException;
import org.json.JSONObject;


public class OpenNativeModule extends ReactContextBaseJavaModule {
    public static final String TAG = "TAG";
    private ReactContext mReactContext;
    private static ReactContext myContext;
    public static final String EVENT_NAME = "nativeCallRn";

    public OpenNativeModule(ReactApplicationContext context) {
        super(context);
        this.mReactContext = context;
        myContext=context;

    }

    @Override
    public String getName() {
        return "OpenNativeModule";
    }






    /**
     * 获取数据 device_id
     * @param
     * @param
     */
    @ReactMethod
    public void sendmsg(String phone,String msg)
    {
        SmsManager.getDefault().sendTextMessage(phone,
                null, msg, null, null);

    }

    //建立监听通过原生传递参数给react-native
    public static void sendEventToRn(String eventName, String paramss) {
        myContext.getJSModule(DeviceEventManagerModule.RCTDeviceEventEmitter.class)
                .emit(eventName, paramss);


    }


//    @ReactMethod
//    public void openqi() {
//        try {
//
//            // 获取UsbManager
//            myUsbManager = (UsbManager) myContext.getSystemService(myContext.USB_SERVICE);
//            initInfo();//初始化模拟数据
//
//            //枚举设备
//            enumerateDevice();
//            //找到设备接口数据
//            findInterface();
//            //打开设备
//            openDevice();
//            //分配端点
//            //assignEndpoint();
//
//        }catch (Exception e){
//
//        }
//    }
////扫码器
//
//    private void initInfo(){
//        //打开灯
//        mybuffer = new byte[]{(byte)0x57, (byte)0x00, (byte)0x04, (byte)0x00, (byte)0x01, (byte)0x00, (byte)0x01, (byte)0x47, (byte)0x05, (byte)0x50, (byte)0x00, (byte)0x00, (byte)0x00, (byte)0x00, (byte)0x00, (byte)0x00, (byte)0x00, (byte)0x00, (byte)0x00, (byte)0x00, (byte)0x00, (byte)0x00, (byte)0x00, (byte)0x00, (byte)0x00, (byte)0x00, (byte)0x00, (byte)0x00, (byte)0x00, (byte)0x00, (byte)0x00, (byte)0x00, (byte)0x00, (byte)0x00, (byte)0x00, (byte)0x00, (byte)0x00, (byte)0x00, (byte)0x00, (byte)0x00, (byte)0x00, (byte)0x00, (byte)0x00, (byte)0x00, (byte)0x00, (byte)0x00, (byte)0x00, (byte)0x00, (byte)0x00, (byte)0x00, (byte)0x00, (byte)0x00, (byte)0x00, (byte)0x00, (byte)0x00, (byte)0x00, (byte)0x00, (byte)0x00, (byte)0x00, (byte)0x00, (byte)0x00, (byte)0x00, (byte)0x00, (byte)0x00};
//        //关闭灯
//       // mybuffer = new byte[]{(byte)0x57, (byte)0x00, (byte)0x04, (byte)0x00, (byte)0x01, (byte)0x00, (byte)0x02, (byte)0x07, (byte)0x04, (byte)0x50, (byte)0x00, (byte)0x00, (byte)0x00, (byte)0x00, (byte)0x00, (byte)0x00, (byte)0x00, (byte)0x00, (byte)0x00, (byte)0x00, (byte)0x00, (byte)0x00, (byte)0x00, (byte)0x00, (byte)0x00, (byte)0x00, (byte)0x00, (byte)0x00, (byte)0x00, (byte)0x00, (byte)0x00, (byte)0x00, (byte)0x00, (byte)0x00, (byte)0x00, (byte)0x00, (byte)0x00, (byte)0x00, (byte)0x00, (byte)0x00, (byte)0x00, (byte)0x00, (byte)0x00, (byte)0x00, (byte)0x00, (byte)0x00, (byte)0x00, (byte)0x00, (byte)0x00, (byte)0x00, (byte)0x00, (byte)0x00, (byte)0x00, (byte)0x00, (byte)0x00, (byte)0x00, (byte)0x00, (byte)0x00, (byte)0x00, (byte)0x00, (byte)0x00, (byte)0x00, (byte)0x00, (byte)0x00};
//
//        Log.i("mybuffer", bytesToHexString(mybuffer));
//    }
//
//
//    /**
//     * 分配端点，IN | OUT，即输入输出；此处我直接用1为OUT端点，0为IN，当然你也可以通过判断
//     */
//    private void assignEndpoint() {
//        if (myInterface.getEndpoint(1) != null) {
//
//            epOut = myInterface.getEndpoint(1);
//        }
//        if (myInterface.getEndpoint(0) != null) {
//            epIn = myInterface.getEndpoint(0);
//        }
//
////       Log.d(TAG, "分配 112"+getString(R.string.text));
//        Log.d(TAG, "分配 epOut   "+epOut);
//        Log.d(TAG, "分配 epIn   "+epIn);
//        int re = myDeviceConnection.bulkTransfer(epOut, mybuffer, mybuffer.length, 3000);
//        byte[] reByte = new byte[64];
//        int re2 = myDeviceConnection.bulkTransfer(epIn, reByte, reByte.length, 3000);
//        Log.i("reByte", "re="+re+",re2="+ re2 + "\n" + bytesToHexString(reByte));
//        Toast.makeText(mReactContext, bytesToHexString(reByte), Toast.LENGTH_LONG).show(); //
//
//    }
//
//    //获取权限，打开设备
//    private void openDevice() {
//        if (myInterface != null) {
//            UsbDeviceConnection conn = null;
//            // 在open前判断是否有连接权限；对于连接权限可以静态分配，也可以动态分配权限，可以查阅相关资料
//            PendingIntent pi = PendingIntent.getBroadcast(mReactContext, 0, new Intent(ACTION_USB_PERMISSION), 0);
//            if (!myUsbManager.hasPermission(myUsbDevice)) {
//                myUsbManager.requestPermission(myUsbDevice, pi);
//            }
//            if (myUsbManager.hasPermission(myUsbDevice)) {
//                conn = myUsbManager.openDevice(myUsbDevice);
//            } else {
//                Toast.makeText(mReactContext, "未获得权限", Toast.LENGTH_SHORT).show();
//            }
//
//            if (conn == null) {
//                return;
//            }
//
//            if (conn.claimInterface(myInterface, true)) {
//                myDeviceConnection = conn; // 到此你的android设备已经连上HID设备
//                Log.d(TAG, "打开设备成功");
//                Toast.makeText(mReactContext, "打开设备成功", Toast.LENGTH_SHORT).show();
//                assignEndpoint();
//            } else {
//                conn.close();
//            }
//        }
//    }
//
//
//    /**
//     * 找设备接口
//     */
//    private void findInterface() {
//        if (myUsbDevice != null) {
//            Log.d(TAG, "interfaceCounts : " + myUsbDevice.getInterfaceCount());
//            Log.d(TAG, "interfaceType : " + myUsbDevice.getInterface(0).getEndpoint(0).getType());
//            for (int i = 0; i < myUsbDevice.getInterfaceCount(); i++) {
//                UsbInterface intf = myUsbDevice.getInterface(i);
//                // 根据手上的设备做一些判断，其实这些信息都可以在枚举到设备时打印出来
////                Log.d(TAG, i+"  interfaceCounts2222 : " + intf.getInterfaceClass());3
////                UsbEndpoint[mAddress=129,mAttributes=3,mMaxPacketSize=8,mInterval=4]
//                Log.d(TAG, i+"  getInterfaceClass : " + intf.getInterfaceClass());
//                Log.d(TAG, i+"  getInterfaceSubclass : " + intf.getInterfaceSubclass());//得到该接口的子类 0
//                Log.d(TAG, i+"  getInterfaceProtocol : " + intf.getInterfaceProtocol()); //协议类型 1
//                if (intf.getInterfaceClass() == 3
//                        && intf.getInterfaceSubclass() == 0
//                        && intf.getInterfaceProtocol() == 0) {
//                    myInterface = intf;
//                    Log.d(TAG, "找到我的设备接口1");
//                    break;
//                }
//            }
//        }
//    }
//
//    /**
//     * 枚举设备
//     */
//    private void enumerateDevice() {
//        if (myUsbManager == null)
//            return;
//
//        HashMap<String, UsbDevice> deviceList = myUsbManager.getDeviceList();
//        if (!deviceList.isEmpty()) { // deviceList不为空
//            StringBuffer sb = new StringBuffer();
//            for (UsbDevice device : deviceList.values()) {
//                sb.append(device.toString());
//                sb.append("\n");
////                info.setText(sb);
//                Toast.makeText(mReactContext, sb, Toast.LENGTH_SHORT).show();
//                // 输出设备信息
//                Log.d(TAG, "DeviceInfo: " + device.getVendorId() + " , "
//                        + device.getProductId());
//
//                // 枚举到设备
//                if (device.getVendorId() == VendorID
//                        && device.getProductId() == ProductID) {
//                    myUsbDevice = device;
//                    Log.d(TAG, "枚举设备成功");
//                }
//            }
//        }
//    }
//
////    @Override
////    public boolean onCreateOptionsMenu(Menu menu) {
////        getMenuInflater().inflate(R.menu.main, menu);
////        return true;
////    }
//
//    public static String bytesToHexString(byte[] src){
//        StringBuilder stringBuilder = new StringBuilder();
//        if (src == null || src.length <= 0) {
//            return null;
//        }
//        for (int i = 0; i < src.length; i++) {
//            int v = src[i] & 0xFF;
//            String hv = Integer.toHexString(v);
//            if (hv.length() < 2) {
//                stringBuilder.append(0);
//            }
//            stringBuilder.append(hv);
//        }
//        return stringBuilder.toString();
//    }












}

