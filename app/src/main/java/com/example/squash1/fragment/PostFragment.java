package com.example.squash1.fragment;


import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.RequestOptions;
import com.example.squash1.R;
import com.theartofdev.edmodo.cropper.CropImage;

import java.io.File;

import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import butterknife.Unbinder;
import pl.aprilapps.easyphotopicker.DefaultCallback;
import pl.aprilapps.easyphotopicker.EasyImage;

import static android.app.Activity.RESULT_OK;


/**
 * A simple {@link Fragment} subclass.
 */
public class PostFragment extends Fragment {

    Context context;
    Activity activity;
    ImageView imgPost;
    Button btnChooseImage, btnSend;
    Unbinder unbinder;
    private PostFragment postFragment;


    private static final int REQUEST_CHOOSE_IMAGE = 3;

    public PostFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View v = LayoutInflater.from(inflater.getContext()).inflate(R.layout.fragment_post, container, false);

//        ButterKnife.bind(activity);
//        unbinder = ButterKnife.bind(activity);

        btnSend = v.findViewById(R.id.btnSend);
        imgPost = v.findViewById(R.id.imgPost);
        btnChooseImage = v.findViewById(R.id.btnChooseImage);

        btnChooseImage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                EasyImage.openChooserWithGallery(PostFragment.this, "Choose Image", REQUEST_CHOOSE_IMAGE);
            }
        });

        return v;
    }

    //bikin hasil
    @Override
    public void onActivityResult(final int requestCode, int resultCode, @Nullable Intent data) {
        EasyImage.handleActivityResult(requestCode, resultCode, data, getActivity(), new DefaultCallback() {

            @Override
            public void onImagePicked(File imageFile, EasyImage.ImageSource source, int type) {
                CropImage.activity(Uri.fromFile(imageFile)).start(getActivity());
            }

            @Override
            public void onImagePickerError(Exception e, EasyImage.ImageSource source, int type) {
                super.onImagePickerError(e, source, type);

                Toast.makeText(getActivity(),"tidak bisa ambil data",Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onCanceled(EasyImage.ImageSource source, int type) {
                super.onCanceled(source, type);
            }
        });

        if (requestCode == CropImage.CROP_IMAGE_ACTIVITY_REQUEST_CODE){
            CropImage.ActivityResult result = CropImage.getActivityResult(data);
            if (resultCode == RESULT_OK){
                final Uri resultUri= result.getUri();
                Glide.with(this).load(new File(resultUri.getPath())).apply(new RequestOptions()).into(imgPost);

                btnSend.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        Intent intent = new Intent(Intent.ACTION_SEND);
                        intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_WHEN_TASK_RESET);
                        intent.setType("image/*");
                        intent.putExtra(Intent.EXTRA_STREAM, resultUri);
                        startActivity(Intent.createChooser(intent, "Share Via"));
                    }
                });
            } else  if (requestCode == CropImage.CROP_IMAGE_ACTIVITY_RESULT_ERROR_CODE){
                Exception error = result.getError();
                Toast.makeText(context, error.toString(), Toast.LENGTH_SHORT).show();
            }
        }
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
//        unbinder.unbind();
    }
}
