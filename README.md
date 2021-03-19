# rnsendmsg



调用自身发短信

添加权限
  <uses-permission android:name="android.permission.INTERNET" />
    <uses-permission android:name="android.permission.SEND_SMS"/>
    <uses-permission android:name="android.permission.READ_SMS" />
    <uses-permission android:name="android.permission.WRITE_SMS" />
    <uses-permission android:name="android.permission.RECEIVE_SMS" />
    <uses-permission android:name="android.permission.RECEIVE_MMS" />
    <uses-permission android:name="android.permission.READ_CONTACTS" />
    
    
    
    在rn-----------MainActivity
    
    
    protected void onCreate(Bundle savedInstanceState) {

    super.onCreate(savedInstanceState);



    if (ContextCompat.checkSelfPermission(MainActivity.this, Manifest.permission.SEND_SMS) != PackageManager.PERMISSION_GRANTED) {
      ActivityCompat.requestPermissions(MainActivity.this, new String[]{Manifest.permission.SEND_SMS}, 1);
    }

    }

    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
      switch (requestCode) {
        case 1:
          if (grantResults.length > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
            //这里写操作 如send（）； send函数中New SendMsg （号码，内容）；
          } else {
            Toast.makeText(this, "你没启动权限", Toast.LENGTH_SHORT).show();
          }
          break;

        default:
      }
    }
  
  
  
  在...Module.java,中添加方法rn 直接调用
  
  
     @ReactMethod
      public void sendmsg(String phone,String msg)
      {
          SmsManager.getDefault().sendTextMessage(phone,
                  null, msg, null, null);

      }
  
  
  
  
  
  
  
  
  
  
  
  
  
  
  
  
  
  
  
  
  
  
  
    
