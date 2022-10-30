package vn.edu.poly.ph26495_mob2022_ass.ADAPTER;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentActivity;
import androidx.fragment.app.FragmentManager;
import androidx.lifecycle.Lifecycle;
import androidx.viewpager2.adapter.FragmentStateAdapter;

import vn.edu.poly.ph26495_mob2022_ass.CHILDFRAGMENT.KhoanThuFra;
import vn.edu.poly.ph26495_mob2022_ass.CHILDFRAGMENT.LoaiThuFra;
import vn.edu.poly.ph26495_mob2022_ass.CHILDFRAGMENT.ThongKeChiFra;
import vn.edu.poly.ph26495_mob2022_ass.CHILDFRAGMENT.ThongKethuPra;

public class AdapterTabLayoutThongKe extends FragmentStateAdapter {


    public AdapterTabLayoutThongKe(@NonNull FragmentActivity fragmentActivity) {
        super(fragmentActivity);
    }

    public AdapterTabLayoutThongKe(@NonNull Fragment fragment) {
        super(fragment);
    }

    public AdapterTabLayoutThongKe(@NonNull FragmentManager fragmentManager, @NonNull Lifecycle lifecycle) {
        super(fragmentManager, lifecycle);
    }

    @NonNull
    @Override
    public Fragment createFragment(int position) {
        if(position == 1){
            return new ThongKeChiFra();
        }
        return new ThongKethuPra();
    }

    @Override
    public int getItemCount() {
        return 2;
    }
}
