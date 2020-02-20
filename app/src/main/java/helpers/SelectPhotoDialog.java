package helpers;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.DialogFragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.nested.R;

import static android.support.constraint.Constraints.TAG;

public class SelectPhotoDialog extends DialogFragment {

    private static final int PICKFILE_REQUEST_CODE = 1;
    private static final int CAMERA_REQUEST_CODE = 2;

    public interface onPhotoSelectedListener{
        void getImagePath(Uri imagePath);
        void getImageBitmap(Bitmap bitmap);
    }
    onPhotoSelectedListener monPhotoSelectedListener;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.dialog_selectphoto, container, false);

        TextView selectPhoto = view.findViewById(R.id.dialogOpenLibrary);
        selectPhoto.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(Intent.ACTION_GET_CONTENT);
                intent.setType("image/*");
                startActivityForResult(intent, PICKFILE_REQUEST_CODE);
            }
        });

        return view;
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if(requestCode == PICKFILE_REQUEST_CODE && resultCode == Activity.RESULT_OK){
            Uri selectedImageUri = data.getData();

            monPhotoSelectedListener.getImagePath(selectedImageUri);
            getDialog().dismiss();

        }else if(requestCode == CAMERA_REQUEST_CODE && resultCode == Activity.RESULT_OK){
            Bitmap bitmap;
            bitmap = (Bitmap) data.getExtras().get("data");

            monPhotoSelectedListener.getImageBitmap(bitmap);
            getDialog().dismiss();
        }
    }

    @Override
    public void onAttach(Context context) {
        try{
            monPhotoSelectedListener = (onPhotoSelectedListener) getTargetFragment();
        }catch (ClassCastException e){
            Log.e(TAG, "err" + e.getMessage());
        }

        super.onAttach(context);
    }
}
