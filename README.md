adb shell am broadcast -a MY.ACTION_SCREEN_ON --es keyCode GET_SCREEN_BRIGHTNESS com.example.tvremote    

关闭麦克风  
adb shell am broadcast -a MY.ACTION_AUDIO --es keyCode SET_MIKE_OFF com.example.tvremote    