package gabriel.oprinoiu.excol2;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import java.net.ServerSocket;

public class MainActivity extends AppCompatActivity {

    ServerSocket serverSocket;
    public ServerThread serverThread;
    public EditText serverPortEditText;
    public EditText cityEditText;
    public EditText clientAddressEditText;
    public EditText clientPortEditText;
    public Button connectButton;
    public Button getWeatherForecastButton;
    public ClientThread clientThread;
    public TextView weatherForecastTextView;
    Spinner spinner;

    ConnectButtonClickListener connectButtonClickListener = new ConnectButtonClickListener();

    private class ConnectButtonClickListener implements Button.OnClickListener{

        @Override
        public void onClick(View v) {
            String serverPort = serverPortEditText.getText().toString();
            if(serverPort == null || serverPort.isEmpty()) {
                Toast.makeText(getApplicationContext(), "[MAIN ACTIVITY] Server port should be filled!", Toast.LENGTH_SHORT).show();
                return;
            }
            serverThread = new ServerThread(Integer.parseInt(serverPort));
            if(serverThread.getServerSocket() == null){
                Log.e("Main", "[MAIN ACTIVITY] Could not create server thread!");
                return;
            }
            serverThread.start();
        }
    }


    GetWeatherButtonClickListener getWeatherButtonClickListener = new GetWeatherButtonClickListener();

    private class GetWeatherButtonClickListener implements Button.OnClickListener{

        @Override
        public void onClick(View v) {
            String address = clientAddressEditText.getText().toString();
            String port = clientPortEditText.getText().toString();
            String city = cityEditText.getText().toString();
            String informationType = spinner.getTransitionName();

            clientThread = new ClientThread(
                    address, Integer.parseInt(port), city, informationType, weatherForecastTextView);
            clientThread.start();
        }
    }




    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        serverPortEditText = (EditText)findViewById(R.id.server_port_edit_text);
        connectButton = (Button)findViewById(R.id.connect_button);
        getWeatherForecastButton = (Button)findViewById(R.id.get_weather_forecast_button);
        cityEditText = (EditText)findViewById(R.id.city_edit_text);
        clientAddressEditText = (EditText)findViewById(R.id.client_address_edit_text);
        clientPortEditText = (EditText)findViewById(R.id.client_port_edit_text);
        spinner = (Spinner)findViewById((R.id.information_type_spinner));
        weatherForecastTextView = (TextView)findViewById(R.id.weather_forecast_text_view);

        connectButton.setOnClickListener(connectButtonClickListener);
        getWeatherForecastButton.setOnClickListener(getWeatherButtonClickListener);
    }

    @Override
    protected void onDestroy() {
        Log.i("Main Activity", "[MAIN ACTIVITY] onDestroy() callback method has been invoked");
        if (serverThread != null) {
            serverThread.stopThread();
        }
        super.onDestroy();
    }
}
