package my.edu.utar.recipeassignment;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.design.widget.BottomNavigationView;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import java.util.List;
import java.util.Map;

import my.edu.utar.recipeassignment.Adapters.SQLiteAdapter;
import my.edu.utar.recipeassignment.Adapters.ShoppingCartAdapter;

public class ShoppingCart extends AppCompatActivity {

    private BottomNavigationView bottomNavigationView;
    private RecyclerView recyclerView;
    private ShoppingCartAdapter adapter;
    private boolean isIngredientsVisible = false;
    private SQLiteAdapter mySQLiteAdapter;
    private SharedPreferences sharedPreferences;
    private static final String PREF_SELECTED_NAV_ITEM_INDEX = "selected_nav_item_index";
    Intent intent;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_shopping_cart);
        sharedPreferences = getSharedPreferences("my_prefs", MODE_PRIVATE);

        // Initialize the RecyclerView
        recyclerView = findViewById(R.id.ingredientRecyclerView);
        bottomNavigationView = findViewById(R.id.bottomNavigationView);
        intent = new Intent(ShoppingCart.this, RecipeDetailsActivity.class); // Initialize the Intent

        int selectedIndex = getSelectedNavItemIndex(0);

        bottomNavigationView.getMenu().getItem(selectedIndex).setChecked(true);

        bottomNavigationView.setOnNavigationItemSelectedListener(item -> {
            switch (item.getItemId()){

                case R.id.home:
                    Intent intent_home = new Intent(ShoppingCart.this, MainActivity.class);
                    startActivity(intent_home);
                    return true;

                case R.id.favourite:
                    Intent intent_fav = new Intent(ShoppingCart.this, FavouriteActivity.class);
                    startActivity(intent_fav);
                    return true;

                case R.id.cart:
                    saveSelectedNavItemIndex(2);
                    return true;

                case R.id.calendar:
                    return true;

                case R.id.settings:
                    return true;

            }
            return false;
        });

        sharedPreferences = getSharedPreferences("my_prefs", MODE_PRIVATE);

        // Initialize the SQLiteAdapter
        mySQLiteAdapter = new SQLiteAdapter(this);
        mySQLiteAdapter.openToRead();

        Map<String, List<String>> shoppingList = mySQLiteAdapter.getShoppingListData();

        System.out.println("Result:" + shoppingList);

        adapter = new ShoppingCartAdapter(this, shoppingList);

        mySQLiteAdapter.close();

        if (recyclerView != null) {
            recyclerView.setHasFixedSize(true);
            recyclerView.setLayoutManager(new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false));
            recyclerView.setAdapter(adapter);
        }else{
            System.out.println("recyclerView is null.");
        }

        System.out.println("shoppingCart.java: " + shoppingList);

    }

    private void saveSelectedNavItemIndex(int selectedIndex) {
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.putInt(PREF_SELECTED_NAV_ITEM_INDEX, selectedIndex);
        editor.apply();
    }

    // Function to retrieve the selected item index from SharedPreferences
    private int getSelectedNavItemIndex(int defaultValue) {
        return sharedPreferences.getInt(PREF_SELECTED_NAV_ITEM_INDEX, defaultValue);
    }
}
