package com.ngohoang.along.appcore.presentation.nytimes.viewmodel.mapper;


import android.util.Log;

import com.ngohoang.along.appcore.data.nytimes.model.Doc;
import com.ngohoang.along.appcore.presentation.nytimes.viewmodel.BaseVM;
import com.ngohoang.along.appcore.presentation.nytimes.viewmodel.DocNoImageVM;
import com.ngohoang.along.appcore.presentation.nytimes.viewmodel.DocVM;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Long on 10/5/2016.
 */

public class Mapper {
    private static final String TAG = "Mapper";
    public static BaseVM tranToDocVM(Doc doc){
        Log.d(TAG, "tranToDocVM: "+doc.toString());
        return new DocVM(doc);
    }
    public static BaseVM tranToDocNoImageVM(Doc doc){
        Log.d(TAG, "tranToDocVM: "+doc.toString());
        return new DocNoImageVM(doc);
    }
    public static List<BaseVM> tranToVM(List<Doc> docList){
        List<BaseVM> list = new ArrayList<>();

        for (Doc item :docList) {
            if(item.getMultimedia().size()==0){
                list.add(tranToDocNoImageVM(item));
            }else {
                list.add(tranToDocVM(item));
            }
        }
        return list;
    }




}
