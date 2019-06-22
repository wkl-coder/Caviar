package com.vrexlab.caviar.fragments;


import android.app.AlertDialog;
import android.content.ContentResolver;
import android.content.ContentValues;
import android.content.DialogInterface;
import android.content.Intent;
import android.database.Cursor;
import android.graphics.BitmapFactory;
import android.media.MediaScannerConnection;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.os.Environment;
import android.provider.MediaStore;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.text.TextUtils;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;


import com.vrexlab.caviar.R;
import com.vrexlab.caviar.utils.RobotoTextView;

import java.io.File;
import java.util.Date;
import java.util.Objects;

import butterknife.Bind;
import butterknife.ButterKnife;

/**
 * A simple {@link Fragment} subclass.
 */
public class FeedContentFragment extends Fragment {


    @Bind(R.id.back)
    ImageView back;
    @Bind(R.id.send)
    RobotoTextView send;
    @Bind(R.id.select_title)
    LinearLayout selectTitle;
    @Bind(R.id.content_edit)
    EditText contentEdit;
    @Bind(R.id.image_support)
    ImageView imageSupport;
    @Bind(R.id.upload_picture)
    TextView uploadPicture;
    @Bind(R.id.title)
    public TextView title;

    public FeedContentFragment() {
        // Required empty public constructor
    }

    private View rootView;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        if (rootView == null) {
            rootView = inflater.inflate(R.layout.fragment_feed_content, container, false);
            ButterKnife.bind(this, rootView);
        } else {
            ButterKnife.bind(this, rootView);
        }
        contentEdit.setSingleLine(false);
//水平滚动设置为False
        contentEdit.setHorizontallyScrolling(false);
        selectTitle.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                FragmentManager fm = Objects.requireNonNull(getActivity()).getSupportFragmentManager();
                assert fm != null;
                FragmentTransaction fragmentTransaction = fm.beginTransaction();
                fragmentTransaction.add(R.id.frame_live, new FeedbackTitleFragment()).addToBackStack(null).commit();
            }
        });
        uploadPicture.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                AlertDialog alertDialog = new AlertDialog.Builder(getActivity()).setMessage(Objects.requireNonNull(getActivity()).getString(R.string.uplpad_picture)).setPositiveButton(getActivity().getString(R.string.Gallery), new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        chooseImage();
                    }
                }).setNegativeButton(getActivity().getString(R.string.camera), new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        getImageFromCamera();
                    }
                }).create();
                alertDialog.show();
            }
        });
        send.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent data = new Intent(Intent.ACTION_SENDTO);
                data.setData(Uri.parse("mailto:support@vrexlab.com"));
                data.putExtra(Intent.EXTRA_SUBJECT, title.getText().toString());
                data.putExtra(Intent.EXTRA_TEXT, contentEdit.getText().toString());
                String[] tos = {"support@vrexlab.com"};
                data.putExtra(Intent.EXTRA_EMAIL, tos);
                if (!TextUtils.isEmpty(picPath)) {
                    data.putExtra(Intent.EXTRA_STREAM, Uri.parse("file://" + picPath));
                }
                try {
                    startActivity(data);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        });
        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                getActivity().onBackPressed();
            }
        });
        return rootView;
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        if (picPath != null) {
            deleteFile(new File(picPath));
        }
        ButterKnife.unbind(this);
    }

    void chooseImage() {
        Intent intent;
        if (Build.VERSION.SDK_INT < 19) {
            intent = new Intent(Intent.ACTION_GET_CONTENT);
            intent.setType("image/*");

        } else {
            intent = new Intent(Intent.ACTION_PICK, MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
        }
        startActivityForResult(intent, 0);
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (getActivity() != null) {
            if (requestCode == 1) {
                imageSupport.setImageBitmap(BitmapFactory.decodeFile(picPath));
            }
            if (requestCode == 0) {
                //获得图片的uri
                Uri originalUri = data.getData();
                if (originalUri != null) {
                    sendPicByUri(originalUri);
                }

            }
        }

    }

    protected void sendPicByUri(Uri selectedImage) {
        String[] filePathColumn = {MediaStore.Images.Media.DATA};
        Cursor cursor = getActivity().getContentResolver().query(selectedImage, filePathColumn, null, null, null);
        if (cursor != null) {
            cursor.moveToFirst();
            int columnIndex = cursor.getColumnIndex(filePathColumn[0]);
            String picturePath = cursor.getString(columnIndex);
            cursor.close();
            cursor = null;

            if (picturePath == null || picturePath.equals("null")) {
                Toast toast = Toast.makeText(getActivity(), "没找到图片", Toast.LENGTH_SHORT);
                toast.setGravity(Gravity.CENTER, 0, 0);
                toast.show();
                return;
            }
            picPath = picturePath;
        } else {
            File file = new File(selectedImage.getPath());
            if (!file.exists()) {
                Toast toast = Toast.makeText(getActivity(), "没找到图片", Toast.LENGTH_SHORT);
                toast.setGravity(Gravity.CENTER, 0, 0);
                toast.show();
                return;
            }
            picPath = file.getAbsolutePath();
        }
        imageSupport.setImageBitmap(BitmapFactory.decodeFile(picPath));
    }

    private String picPath;

    protected void getImageFromCamera() {
        Intent getImageByCamera = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);

        if (Build.VERSION.SDK_INT < 24) {

            picPath = getPicName();
            // 指定输出路径
            getImageByCamera.putExtra(MediaStore.EXTRA_OUTPUT, Uri.fromFile(new File(picPath)));
            getImageByCamera.putExtra(MediaStore.EXTRA_VIDEO_QUALITY, 1);
            startActivityForResult(getImageByCamera, 1);

        } else {
            picPath = getPicName();
            ContentValues contentValues = new ContentValues(1);
            contentValues.put(MediaStore.Images.Media.DATA, new File(picPath).getAbsolutePath());
            Uri uri = getContext().getContentResolver().insert(MediaStore.Images.Media.EXTERNAL_CONTENT_URI, contentValues);
            getImageByCamera.putExtra(MediaStore.EXTRA_OUTPUT, uri);
            startActivityForResult(getImageByCamera, 1);
        }


    }

    public String getPicName() {
        Date date = new Date();
        return Environment.getExternalStorageDirectory().toString() + "/" + date.getTime() + ".jpg";
    }

    public void deleteFile(File file) {
        if (!TextUtils.isEmpty(file.getAbsolutePath())) {
            file.delete();
            Uri uri = MediaStore.Images.Media.EXTERNAL_CONTENT_URI;
            ContentResolver mContentResolver = getActivity().getContentResolver();
            String where = MediaStore.Images.Media.DATA + "='" + file.getAbsolutePath() + "'";
//删除图片
            mContentResolver.delete(uri, where, null);
            MediaScannerConnection.scanFile(getActivity(),
                    new String[]{file.getAbsolutePath()}, null,
                    new MediaScannerConnection.OnScanCompletedListener() {
                        @Override
                        public void onScanCompleted(final String path, final Uri uri) {


                        }
                    });


        }

    }
}
