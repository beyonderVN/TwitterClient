package com.longngohoang.twitter.mobile.ui.dailog;

import android.app.DatePickerDialog;
import android.graphics.Point;
import android.os.Bundle;
import android.support.v4.app.DialogFragment;
import android.view.Display;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.view.WindowManager;
import android.widget.ArrayAdapter;
import android.widget.CheckBox;
import android.widget.DatePicker;
import android.widget.Spinner;
import android.widget.TextView;

import com.longngohoang.twitter.appcore.data.model.SearchRequest;
import com.longngohoang.twitter.mobile.R;
import com.longngohoang.twitter.mobile.databinding.LayoutSearchFilterBinding;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Locale;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;


/**
 * Created by Admin on 23/10/2016.
 */

public class ChangeOptionDailog extends DialogFragment {
    private static final String TAG = "ChangeOptionDailog";

    public static final int DATE_PICKER_DIALOG = 1;
    public static final String SEARCH_REQUEST = "SEARCH_REQUEST";
    LayoutSearchFilterBinding layoutSearchFilterBinding;

    @BindView(R.id.tvBeginDate)
    TextView tvBeginDate;
    @BindView(R.id.spnSort)
    Spinner spnSort;
    @BindView(R.id.cbArts)
    CheckBox cbArts;
    @BindView(R.id.cbFashion)
    CheckBox cbFashion;
    @BindView(R.id.cbSports)
    CheckBox cbSports;

    ChangeOptionDialogListener changeOptionDialogListener;
    SearchRequest searchRequest;
    public ChangeOptionDailog(){

    }
    ChangeOptionDailog(ChangeOptionDialogListener changeOptionDialogListener){
        this.changeOptionDialogListener = changeOptionDialogListener;
    }
    public static ChangeOptionDailog newInstance(SearchRequest searchRequest,ChangeOptionDialogListener changeOptionDialogListener) {
        ChangeOptionDailog f = new ChangeOptionDailog(changeOptionDialogListener);
        Bundle bdl = new Bundle();
        bdl.putSerializable(SEARCH_REQUEST, searchRequest);
        f.setArguments(bdl);

        return f;
    }

    @Override
    public View onCreateView(
            LayoutInflater inflater,
            ViewGroup container,
            Bundle savedInstanceState
    ) {
        View v = inflater.inflate(
                R.layout.layout_search_filter, container, false);
        ButterKnife.bind(this, v);
        Bundle bundle = getArguments();
        searchRequest = (SearchRequest) bundle.getSerializable(SEARCH_REQUEST);


        //setup binding
        layoutSearchFilterBinding = LayoutSearchFilterBinding.bind(v);
        layoutSearchFilterBinding.setSearchRequest(searchRequest);
        layoutSearchFilterBinding.executePendingBindings();

        ArrayAdapter<String> adapter = new ArrayAdapter<String>(getContext(),android.R.layout.simple_spinner_item,searchRequest.getSortList());
        spnSort.setAdapter(adapter);
        spnSort.setSelection(searchRequest.getCurrentSort());
        //setup event handle

        onDatePickerBtnClick();

        return v;
    }
    public void onResume() {
        // Store access variables for window and blank point
        Window window = getDialog().getWindow();
        Point size = new Point();
        // Store dimensions of the screen in `size`
        Display display = window.getWindowManager().getDefaultDisplay();
        display.getSize(size);
        // Set the width of the dialog proportional to 9% of the screen width
        window.setLayout((int) (size.x * 0.9), WindowManager.LayoutParams.WRAP_CONTENT);
        window.setGravity(Gravity.CENTER);
        // Call super onResume after sizing
        super.onResume();
    }
    private DatePickerDialog.OnDateSetListener myDateListener = new DatePickerDialog.OnDateSetListener() {
        @Override
        public void onDateSet(DatePicker arg0, int arg1, int arg2, int arg3) {
            myCalendar.set(Calendar.YEAR, arg1);
            myCalendar.set(Calendar.MONTH, arg2);
            myCalendar.set(Calendar.DAY_OF_MONTH, arg3);
            updateLabel();
        }
    };
    private void onDatePickerBtnClick() {
        tvBeginDate.setOnClickListener(v -> {
            final Calendar c = Calendar.getInstance();
            int year = c.get(Calendar.YEAR);
            int month = c.get(Calendar.MONTH);
            int day = c.get(Calendar.DAY_OF_MONTH);
            (new DatePickerDialog(getActivity(), myDateListener, year, month, day)).show();
        });
    }
    @OnClick(R.id.btSave)
    void onSubmitClick() {
        saveOption();
        changeOptionDialogListener.onFinishChangeOption(searchRequest);
        dismiss();
    }


    @OnClick(R.id.btSaveSearch)
    void onSearchSubmitClick() {
        saveOption();
        changeOptionDialogListener.onFinishChangeSearchOption(searchRequest);
        dismiss();
    }
    private void saveOption() {
        searchRequest.setBeginDate(tvBeginDate.getText().toString());
        searchRequest.setCurrentSort(spnSort.getSelectedItemPosition());
        searchRequest.getFq().get(0).setChecked(cbArts.isChecked());
        searchRequest.getFq().get(1).setChecked(cbFashion.isChecked());
        searchRequest.getFq().get(2).setChecked(cbSports.isChecked());

    }

    Calendar myCalendar = Calendar.getInstance();

    private void updateLabel() {
        String myFormat = "dd/MM/yyyy"; //In which you need put here
        SimpleDateFormat sdf = new SimpleDateFormat(myFormat, Locale.US);

        tvBeginDate.setText(sdf.format(myCalendar.getTime()));
    }


    public interface ChangeOptionDialogListener {
        void onFinishChangeOption(SearchRequest searchRequest);
        void onFinishChangeSearchOption(SearchRequest searchRequest);
    }

}

