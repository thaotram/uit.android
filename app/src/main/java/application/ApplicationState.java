package application;

import android.databinding.BaseObservable;
import android.databinding.ObservableField;

public class ApplicationState extends BaseObservable {
    // Đây là những trường dữ liệu được sử dụng khi muốn binding ra
    // Các trường dữ liệu thuộc loại lưu trữ sẽ được lưu ở `class Application Store`
    public final ObservableField<String> status = new ObservableField<>();

    public ApplicationState() {
    }
}

