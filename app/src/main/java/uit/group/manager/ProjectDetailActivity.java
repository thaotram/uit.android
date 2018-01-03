package uit.group.manager;

import android.content.Intent;
import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;

import module.object.Project;
import uit.group.manager.databinding.ActivityProjectDetailBinding;
import view.adapter.UserRecyclerViewAdapter;
import view.state.ProjectDetailState;

//import module.object.Channel;

public class ProjectDetailActivity extends AppCompatActivity {

    private ProjectDetailState state = new ProjectDetailState();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_project_detail);

        InitializeDataBinding();
        InitializeIntent();

        InitializeChannelsRecyclerView();
        InitializeTasksRecyclerView();
        InitializeMembersRecyclerView();
    }

    private void InitializeIntent() {
        Intent intent = getIntent();
        int projectId = intent.getIntExtra("projectId", -1);
        if (projectId != -1) {
            state.project.set(Project.getProjectById(projectId));
        }
    }

    private void InitializeChannelsRecyclerView() {
//        ChannelRecyclerViewAdapter adapter = new ChannelRecyclerViewAdapter(state.project.get().getChannels());
//        RecyclerView recyclerView = findViewById(R.id.list_channels);
//        recyclerView.setLayoutManager(new LinearLayoutManager(this));
//        recyclerView.setAdapter(adapter);
//        recyclerView.setHasFixedSize(true);
    }

    private void InitializeTasksRecyclerView() {
//        TaskRecyclerViewAdapter adapter = new TaskRecyclerViewAdapter(state.project.get().getTasks());
//        RecyclerView recyclerView = findViewById(R.id.list_tasks);
//        recyclerView.setLayoutManager(new LinearLayoutManager(this));
//        recyclerView.setAdapter(adapter);
//        recyclerView.setHasFixedSize(true);
    }

    private void InitializeMembersRecyclerView() {
        UserRecyclerViewAdapter adapter = new UserRecyclerViewAdapter(state.project.get().getMembers());
        RecyclerView recyclerView = findViewById(R.id.list_members);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerView.setAdapter(adapter);
        recyclerView.setHasFixedSize(true);
    }

    private void InitializeDataBinding() {
        ActivityProjectDetailBinding binding = DataBindingUtil.setContentView(this, R.layout.activity_project_detail);
        binding.setState(state);
    }

    public void goBackToMainActivity(View view) {
        startActivity(new Intent(getBaseContext(), MainActivity.class));
        finish();
    }
}
