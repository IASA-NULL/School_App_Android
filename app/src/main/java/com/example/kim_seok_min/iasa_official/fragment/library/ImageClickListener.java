package com.example.kim_seok_min.iasa_official.fragment.library;

import android.content.Context;
import android.content.Intent;
import android.view.View;

public class ImageClickListener implements View.OnClickListener {
    Context context;

    //-----------------------------------------------------------
    // imageID는 확대해서 보여줄 이미지의 리소스 ID입니다.

    int imageID;

    public ImageClickListener(Context context, int imageID) {
        this.context = context;
        this.imageID = imageID;
    }



    public void onClick(View v) {
        //---------------------------------------------------------
        // 확대된 이미지를 보여주는 액티비티를 실행하기 위해 인텐트 객체를 정의합니다.
        // 그리고 이 액티비티에 전달할 imageID의 값을 이 객체에 저장합니다.
        // 인텐트 객체를 정의 후 이 액티비티를 실행합니다.

        Intent intent = new Intent(context, ImageActivity.class);
        intent.putExtra("image ID", imageID);
        context.startActivity(intent);
    }

}
