package vn.edu.poly.ph26495_mob2022_ass.ADAPTER;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentActivity;
import androidx.fragment.app.FragmentManager;
import androidx.lifecycle.Lifecycle;
import androidx.viewpager2.adapter.FragmentStateAdapter;

import vn.edu.poly.ph26495_mob2022_ass.CHILDFRAGMENT.KhoanChiFra;
import vn.edu.poly.ph26495_mob2022_ass.CHILDFRAGMENT.KhoanThuFra;
import vn.edu.poly.ph26495_mob2022_ass.CHILDFRAGMENT.LoaiChiFra;
import vn.edu.poly.ph26495_mob2022_ass.CHILDFRAGMENT.LoaiThuFra;

public class AdapterTabLayoutChi extends FragmentStateAdapter {


    public AdapterTabLayoutChi(@NonNull FragmentActivity fragmentActivity) {
        super(fragmentActivity);
    }

    public AdapterTabLayoutChi(@NonNull Fragment fragment) {
        super(fragment);
    }

    public AdapterTabLayoutChi(@NonNull FragmentManager fragmentManager, @NonNull Lifecycle lifecycle) {
        super(fragmentManager, lifecycle);
    }

    @NonNull
    @Override
    public Fragment createFragment(int position) {
        if(position == 1){
            return new  LoaiChiFra();
        }
        return new KhoanChiFra();
    }

    @Override
    public int getItemCount() {
        return 2;
    }
}
