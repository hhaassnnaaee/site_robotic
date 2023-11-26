package MDP;

import java.util.List;

public interface IDAO<T> {
    	// Create
    	boolean save(T item);
    	boolean saveAll(List<T> items) ;
    	// Read
    	T getById(int id);
    	List<T> getAll();
    	// Update
    	boolean update(T item) ;
    	
    
    	
    	//authentifier
        String authentifier(List<T> items);

    	
    	//add
		void add(List<T> items);
		
		//Singin
		String signin(List<T> items);
		
		//chercher
		String chercher(List<T> items);

		


		
}