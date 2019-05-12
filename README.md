[![](https://jitpack.io/v/cugkuan/LineProgress.svg)](https://jitpack.io/#cugkuan/LineProgress)

# LineProgress
一个进度指示器

![image](https://github.com/cugkuan/LineProgress/blob/master/pic/image.jpeg)

# 添加依赖库
```
implementation 'com.github.cugkuan:LineProgress:1.0.0'
```

# 使用

## 在xml中
```
 <com.cugkuan.widget.LineProgressView
            android:layout_width="match_parent"
            app:ll_dividerWidth="5dp"
            app:ll_dividerColor="@android:color/holo_red_light"
            android:layout_height="40dp" />
```

## 属性说明

|编号|属性|说明|
|--|--|
|1|ll_progressColor|进度条的颜色|
|2|ll_color|默认的颜色，绘制非进度的颜色|
|3|ll_dividerWidth|分割线的间隔|
|4|ll_dividerColor|分割线的颜色|
|5|ll_max|最大的值
|6|ll_progress|当前进度值|
