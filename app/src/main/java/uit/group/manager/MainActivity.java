package uit.group.manager;

import android.content.Intent;
import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;

import org.parceler.Parcels;

import module.facebook._Facebook;
import module.socket._Socket;
import module.socket._Socket_Project;
import object.Project;
import object.User;
import uit.group.manager.databinding.ActivityMainBinding;
import view.recyclerViewAdapter.ProjectRecyclerViewAdapter;

public class MainActivity extends AppCompatActivity {
    private User user;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        InitializeUser();
        InitializeDataBinding();
        InitializeRecyclerView();
    }

    private void InitializeUser() {
        user = Parcels.unwrap(getIntent().getParcelableExtra("user"));
    }

    private void InitializeDataBinding() {
        ActivityMainBinding binding = DataBindingUtil.setContentView(this, R.layout.activity_main);
        binding.setSocket(_Socket.State.getInstance());
        binding.setUser(user);
    }

    private void InitializeRecyclerView() {
        RecyclerView recyclerView = findViewById(R.id.list_project);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerView.setAdapter(new ProjectRecyclerViewAdapter(user.getProjects()));
        recyclerView.setHasFixedSize(true);
    }

    public void facebookLogout(View view) {
        _Facebook.Logout();
        startActivity(new Intent(this, LoginActivity.class));
        finish();
    }

    public void createProject(View view) {
        Project.Callback project = new Project.Callback() {
            @Override
            public void Response(Project project) {
                Intent intent;

                intent = new Intent(getBaseContext(), ProjectCreateActivity.class);
                intent.putExtra("project", Parcels.wrap(project));

                startActivity(intent);
            }
        };
        _Socket_Project.CreateProject(user.getId(), project);
    }

    public void goToUserInfomation(View view) {
        startActivity(new Intent(this, UserInformationActivity.class));
    }
}