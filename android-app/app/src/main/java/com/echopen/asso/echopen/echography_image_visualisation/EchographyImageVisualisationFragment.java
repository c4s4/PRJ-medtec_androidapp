package com.echopen.asso.echopen.echography_image_visualisation;

import android.support.v4.app.Fragment;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.echopen.asso.echopen.R;
import com.echopen.asso.echopen.view.CaptureButton;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class EchographyImageVisualisationFragment extends Fragment implements EchographyImageVisualisationContract.View{

    public static final String TAG = EchographyImageVisualisationFragment.class.getSimpleName();
    private EchographyImageVisualisationContract.Presenter mEchographyImageVisualisationPresenter;
    /*@BindView(R.id.main_button_capture) ImageView mCaptureButton;
    @OnClick(R.id.main_button_capture)
    public void captureImage(View v){
        Log.d(TAG, "Short Press");
    }*/

    @BindView(R.id.main_preset_configuration) ImageView mPresetConfigurationButton;
    @OnClick(R.id.main_preset_configuration)
    public void presetConfigurationChange(View v){
        Log.d(TAG, "Preset configuration changed");

    }
    @BindView(R.id.main_button_end_exam) ImageView mEndExamButton;
    @OnClick(R.id.main_button_end_exam)
    public void endExam(View v){
        Log.d(TAG, "EndExamButton Pressed");
    }
    @BindView(R.id.main_button_battery) ImageView mBatteryStatus;
    @BindView(R.id.main_selected_organ) ImageView mSelectedOrgan;
    @BindView(R.id.main_button_shadow) CaptureButton mCaptureShadow;


    private final static float IMAGE_ZOOM_FACTOR = 1.75f;
    private final static float IMAGE_ROTATION_FACTOR = 90.f;

    public EchographyImageVisualisationFragment(){

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_main, container, false);

        ButterKnife.bind(this, rootView);

        mCaptureShadow.setListener(new CaptureButton.CaptureButtonListener() {
            @Override
            public void onCancel() {
                Log.d(TAG, "OnCancel");
            }

            @Override
            public void onShortPress() {
                Log.d(TAG, "onShortPress");
            }

            @Override
            public void onLongPress() {
                Log.d(TAG, "onLongPress");
            }

        });

        return rootView;
    }

    @Override
    public void refreshImage(final Bitmap iBitmap) {
        getActivity().runOnUiThread(new Runnable() {
            @Override
            public void run() {

                if(getView() == null){
                    return;
                }

                Log.d(TAG, "refreshImage");
                ImageView lEchOpenImage = (ImageView) getView().findViewById(R.id.echopenImage);
                lEchOpenImage.setRotation(IMAGE_ROTATION_FACTOR);
                lEchOpenImage.setScaleX(IMAGE_ZOOM_FACTOR);
                lEchOpenImage.setScaleY(IMAGE_ZOOM_FACTOR);
                lEchOpenImage.setImageBitmap(iBitmap);
            }
        });
    }

    @Override
    public void displayFreezeButton() {
        mCaptureShadow.setImageResource(R.drawable.icon_arc_shadow);
        //mCaptureButton.setImageResource(R.drawable.button_jauge);
    }

    @Override
    public void displayUnfreezeButton() {
        mCaptureShadow.setImageResource(R.drawable.icon_save_image);
    }

    @Override
    public void setPresenter(EchographyImageVisualisationContract.Presenter iPresenter) {
        mEchographyImageVisualisationPresenter = iPresenter;
        mEchographyImageVisualisationPresenter.start();
    }
}