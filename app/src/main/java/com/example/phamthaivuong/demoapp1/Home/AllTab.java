package com.example.phamthaivuong.demoapp1.Home;

import android.content.Intent;
import android.net.Uri;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.SimpleAdapter;
import android.widget.Toast;

import com.example.phamthaivuong.demoapp1.Adapter.JAVAdapter;
import com.example.phamthaivuong.demoapp1.Connect.HttpHandler;
import com.example.phamthaivuong.demoapp1.DetailActivity;
import com.example.phamthaivuong.demoapp1.MainActivity;
import com.example.phamthaivuong.demoapp1.Model.JAVModel;
import com.example.phamthaivuong.demoapp1.R;
import com.example.phamthaivuong.demoapp1.TestSingleton;
import com.facebook.drawee.view.SimpleDraweeView;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class AllTab extends Fragment {
    private OnFragmentInteractionListener mListener;
    private String TAG  = MainActivity.class.getSimpleName();
    private ListView lv;
    SimpleDraweeView msimpleDraweeView;
    JAVAdapter adapter;

    List<JAVModel> contactLish;

    public interface OnFragmentInteractionListener {
        // TODO: Update argument type and name
        void onFragmentInteraction(Uri uri);
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View pageAllTab = inflater.inflate(R.layout.fragment_alltab,container,false);

        contactLish = new ArrayList<>();
        lv = (ListView)pageAllTab.findViewById(R.id.list);

        new GetContacts().execute();
//         adapter = new JAVAdapter(getActivity(), contactLish) {
//             @Override
//             public void OnCickItemJAV(JAVModel javModel) {
//                 Toast.makeText(getActivity(), "JAVmode " +javModel.getName(), Toast.LENGTH_SHORT).show();
//                 Intent i = new Intent(getActivity(),DetailActivity.class);
//                 i.putExtra("JAV",javModel);
//                 startActivity(i);
//             }
//         };
            adapter = new JAVAdapter(getActivity(),contactLish);
            adapter.setOnCickItemJAV(new JAVAdapter.OnCickItemJAV() {
                @Override
                public void onClick(JAVModel javModel) {
                    Intent i = new Intent(getActivity(),DetailActivity.class);
                    i.putExtra("JAV",javModel);
                    startActivity(i);
                }
            });
         lv.setAdapter(adapter);

        return pageAllTab;
    }
    private class GetContacts extends AsyncTask<Void,Void,Void> {
        @Override
        protected void onPreExecute() {
            super.onPreExecute();
            Toast.makeText(getContext(), "JSON Dữ liệu đang tải xuống", Toast.LENGTH_SHORT).show();
        }

        @Override
        protected Void doInBackground(Void... arg0) {
            HttpHandler sh = new HttpHandler();

            // Tạo một yêu cầu tới url và nhận được phản hồi // Making a request to url and getting response
            String url = "https://api.avgle.com/v1/categories";
            String jsonStr = sh.makeServiceCall(url);

            Log.e(TAG,"Response from URL (Phản hồi từ URL) : " + jsonStr);
            if (jsonStr !=null){
                try{
                    JSONObject jsonObj = new JSONObject(jsonStr);
                    // Lấy json Object
                   JSONObject response = jsonObj.getJSONObject("response");
                    // Lấy nút JSON Array // Getting JSON Array node
                    JSONArray contacts = response.getJSONArray("categories");

                    // List
                    List<JAVModel> listJAV = new ArrayList<>();
                    // lặp qua tất cả Danh bạ // looping through All Contacts
                    for (int i = 0;i<contacts.length();i++){
                        JSONObject c = contacts.getJSONObject(i);

                        String id = String.valueOf(c.getInt("CHID"));
                        String name = c.getString("name");
                        String slug = c.getString("slug");
                        String image = c.getString("cover_url");
                        String total_videos = c.getString("total_videos");
                        String shortname = c.getString("shortname");
                        String category_url = c.getString("category_url");
                        //                        String address = c.getString("address");
//                        String gender = c.getString("gender");
                        // Nút điện thoại là đối tượng JSON // Phone node is JSON Object
//                        JSONObject phone = c.getJSONObject("phone");
//                        String mobile = phone.getString("mobile");
//                        String home = phone.getString("home");
//                        String office = phone.getString("office");

                        // bản đồ băm tmp cho liên hệ đơn // tmp hash map for single contact
//                        HashMap<String,String> contact = new HashMap<>();
//
//                        // thêm từng nút con vào khóa HashMap => value // adding each child node to HashMap key => value
//                        contact.put("id",id);
//                        contact.put("name",name);
//                        contact.put("slug",slug);
//                        contact.put("cover_url",image);


//                        contact.put("email",email);
//                        contact.put("mobile",mobile);
//                        contact.put("address",address);
//                        contact.put("gender",gender);
//                        contact.put("home",home);
//                        contact.put("office",office);

                        // thêm liên hệ vào danh sách liên lạc // adding contact to contact list
                        JAVModel javModel = new JAVModel();
                        javModel.setcHID(id);
                        javModel.setName(name);
                        javModel.setSlug(slug);
                        javModel.setCoverUrl(image);
                        javModel.setTotalVideos(Integer.valueOf(total_videos));
                        javModel.setShortname(shortname);
                        javModel.setCategoryUrl(category_url);

                        listJAV.add(javModel);

                    }
                    contactLish.addAll(listJAV);



                } catch (final JSONException e) {
                    Log.e(TAG,"Json parsing error (Lỗi phân tích cú pháp Json) : " + e.getMessage());
                    runOnUiThread(new Runnable() {
                        @Override
                        public void run() {
                            Toast.makeText(getContext(), "Json parsing error (Lỗi phân tích cú pháp Json) : ", Toast.LENGTH_SHORT).show();
                        }
                    });
                }
            }
            else {
                Log.e(TAG,"Couldn't get json from server. (Không thể lấy json từ máy chủ.) :");
                runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        Toast.makeText(getContext(), "Couldn't get json from server. Check LogCat for possible errors! (Không thể lấy json từ máy chủ. Kiểm tra LogCat để biết các lỗi có thể xảy ra!)", Toast.LENGTH_SHORT).show();
                    }
                });
            }
            return null;
        }

        @Override
        protected void onProgressUpdate(Void... values) {
            super.onProgressUpdate(values);
        }

        @Override
        protected void onPostExecute(Void result) {
            super.onPostExecute(result);

            if (adapter != null){
                adapter.notifyDataSetChanged();
            }

        }
    }

    private void runOnUiThread(Runnable runnable) {
    }

}

