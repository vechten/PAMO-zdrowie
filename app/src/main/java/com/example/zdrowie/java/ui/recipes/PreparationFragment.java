package com.example.zdrowie.java.ui.recipes;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;

import com.example.zdrowie.R;
import com.example.zdrowie.databinding.FragmentPreparationBinding;

import java.util.ArrayList;
import java.util.Arrays;


public class PreparationFragment extends Fragment {

    private FragmentPreparationBinding binding;

    String[] ingredients1 = {
            "8 bone-in chicken thighs , skin removed",
            "3 tbsp chipotle paste",
            "2 garlic cloves , crushed",
            "2 x 400g cans chopped tomatoes",
            "1 large onion , finely sliced",
            "2 x 400g cans black beans , drained",
            "400g cans kidney beans , drained",
    };

    String[] preparation1 = {
            "Remove the roasting tin from the oven, add all the beans and stir",
            "into the tomato mixture around the chicken. Put back in the oven, uncovered,",
            "for 20 mins or until the chicken is tender and the beans are hot.",
    };

    String[] ingredients2 = {
            "2 tbsp olive oil",
            "2 onions, finely chopped",
            "2 garlic cloves, crushed",
            "¼ tsp hot chilli powder",
            "1 tbsp ras el hanout",
            "1 butternut squash, peeled and cut into 2cm pieces",
            "100g red lentils",
            "1l hot vegetable stock",
    };

    String[] preparation2 = {
            "Stir in the squash and lentils. Pour over the stock and season to taste.",
            "Bring to the boil, then reduce the heat to a simmer and cook, covered, for 25 mins or until the squash is soft.",
            "Blitz the soup with a stick blender until smooth, then season to taste.",
            "To freeze, leave to cool completely and transfer to large freezerproof bags."
    };

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {

        binding = FragmentPreparationBinding.inflate(inflater, container, false);
        View root = binding.getRoot();

        ListView ingredientListView1 = (ListView) binding.ingredientsListView1;
        ListView preparationListView1 = (ListView) binding.preparationListView1;

        ArrayList<String> ingredientList1 = new ArrayList<>(Arrays.asList(ingredients1));

        ArrayList<String> preparationList1 = new ArrayList<>(Arrays.asList(preparation1));

        ArrayAdapter<String> adapter1 = new ArrayAdapter<>(getActivity(), R.layout.recipe_item, ingredientList1);
        ArrayAdapter<String> adapter2 = new ArrayAdapter<>(getActivity(), R.layout.preparation_item, preparationList1);

        ingredientListView1.setAdapter(adapter1);
        preparationListView1.setAdapter(adapter2);

        // -----------

        ListView ingredientListView2 = (ListView) binding.ingredientsListView2;
        ListView preparationListView2 = (ListView) binding.preparationListView2;

        ArrayList<String> ingredientList2 = new ArrayList<>(Arrays.asList(ingredients2));

        ArrayList<String> preparationList2 = new ArrayList<>(Arrays.asList(preparation2));

        ArrayAdapter<String> adapter3 = new ArrayAdapter<>(getActivity(), R.layout.recipe_item, ingredientList2);
        ArrayAdapter<String> adapter4 = new ArrayAdapter<>(getActivity(), R.layout.preparation_item, preparationList2);

        ingredientListView2.setAdapter(adapter3);
        preparationListView2.setAdapter(adapter4);

        return root;
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }
}