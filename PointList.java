class PointList {
		private Node first;
		private int dim = 0;
		
		
	    public PointList(int dim) {								// Ο constructor της PointList.
	    	first = null;
	    	this.dim = dim;
	    }
	    
	    public static PointList readData(String filename) {		// WORKS.
	        PointList list = null;
	        java.io.BufferedReader br = null;
	        
	        try {
	            br = new java.io.BufferedReader(new java.io.FileReader(filename));
	            String line = br.readLine();
	            String[] data = line.split(" ");
	            //int m = Integer.parseInt(data[0]);
	            int n = Integer.parseInt(data[1]);
	            
	            list = new PointList(n);
	            double[] x = new double[n];
	            while((line = br.readLine()) != null) {
	                data = line.split(" ");
	                for(int j = 0; j < n; j++)
	                    x[j] = Double.parseDouble(data[j]);
	                list.append(x, Double.parseDouble(data[n]));
	            }
	        }
	        catch (java.io.IOException e) {
	            e.printStackTrace();
	        }
	        finally {
	            try {
	            	if (br != null) br.close();
	            }
	            catch (java.io.IOException ex) {
	            	ex.printStackTrace();
	            }
	        }
	        return list;
	    }
	    
	    
	    public int getDim() {							// WORKS.
	    	return this.dim;							// επιστρέφει τη διάσταση που αποθηκεύονται της λίστας που καλείται η getDim().
	    }
	    
	    
	    public int length() {							// WORKS.
	    	Node current = first;
	    	int count = 0;
	    	
	    	while ( current != null ) {
	    		count++;								// μετράει τα nodes όσο δεν βρίσκει null.
	    		current = current.next;
	    	}
	    	return count;								// επιστρέφει το πλήθος των nodes της λίστας.
	    }
	    
	    
	    private boolean isEmpty() {
			return first == null;
		}
	    
	    
	    public boolean append(double[] x, double y) {	// WORKS.
	    	Node tail = first;
	    	Node newNode = new Node(x, y);
	    	
	    	if ( isEmpty() )							// Αν η λίστα είναι άδεια, κάνε το πρώτο node το καινούριο node.
	    		first = newNode;
	    	else {
	    		while ( tail.next != null )				// βρες το τελευταίο node της λίστας.
	    			tail = tail.next;
	    		
	    		tail.next = newNode;					// κάνε το καινούριο node, το τελευταίο.
	    	}
	    	
	    	return getDim() == x.length;				// επιστρέφει true ή false, αν η διάσταση των σημείων που αποθηκεύει η λίστα ισούται με τη διάσταση του ορίσματος x.
	    }
	    
	    
	    public boolean append(PointList list) {			// WORKS.
	    	if ( list.isEmpty() && this.isEmpty() ) {	// αν και οι δύο λίστες είναι άδειες επέστρεψε απλά true ή false αν αποθηκεύουν οι δύο λίστες σημεία ίδιας διάστασης.
	    		
	    	}
	    	else {
	    		Node current = list.first;				// αποθηκεύει το πρώτο node της list.
	    		if ( current != null ) {				// αν το πρώτο node είναι null σημαίνει οτι είνα άδεια οπότε δεν υπάρχει κάτι να προσθέσει στην this.list.
		    		while ( current != null ) {
			    		this.append(current.ptd.x, current.ptd.y);	// προσθέτει τα data του current node της list στην this.list.
			    		current = current.next;
			    	}
	    		}
	    	}
	    	return getDim() == list.getDim();			// επιστρέφει απλά true ή false αν αποθηκεύουν οι δύο λίστες σημεία ίδιας διάστασης.
	    }
	    
	    
	    public PointData rmFirst() {					// WORKS.
	    	if ( isEmpty() )							// αν η λίστα είναι άδεια, επέστρεψε null.
	    		return null;
	    	else {
	    		Node temp = first;						// αποθηκεύει temporarily το first ώστε να επιστρέψει τα στοιχεία του.
	    		first = first.next;						// κάνε το δεύτερο node, το πρώτο node.
	    		return new PointData(temp.ptd.x, temp.ptd.y);	// επιστρέφει τα data του πρώτου node που διεγράφει.
	    	}
	    }
	    
	    
	    public void shuffle() {
	    	Node current = this.first;
	    	int length = this.length();
	    	int minimum = 1;									// θα είναι το minimum του διαστήματος παραγωγής τυχαίου αριθμού, ξεκινάει από 1 γιατί την πρώτη φορά
	    														// δεν θέλουμε να κάνει το πρώτο swap με τον εαυτό του.
	    	
	    	while ( current != null && minimum < length ) {
	    		// διάλεξε ένα τυχαίο αριθμό από το minimum έως το πλήθος των κόμβων.
	    		// πχ. Την δεύτερη φορά στο διάστημα [2, length], την τρίτη στο [3, length].
	    		int rand = randInt(minimum, length);
	    		Node random = first;							// θα αποθηκεύει το node που πρόκειται να γίνει swap με το current.
	    		int countTillFindNodeToSwap = 1;				// θα μετράει μέχρι να βρει το node που πρόκειται να γίνει swap
	    		
	    		while ( countTillFindNodeToSwap < rand ) {				// όσο δεν πρόκειται για το τυχαίο node που βρέθηκε
	    			random = random.next;								// προχώρα το node μια θέση μπροστά.
	    			countTillFindNodeToSwap++;
	    		}
	    		
	    		PointData temp = current.ptd;						// αλγόριθμος που κάνει swap τα point data της current και του τυχαίου node που επιλέχθηκε.
	    		current.ptd = random.ptd;
	    		random.ptd = temp;
	    		
	    		minimum++;											// μεγαλώνει το minimum του διαστήματος παραγωγής random αριθμού.
	    		current = current.next;
	    	}
	    }
	    
	    
	    private int randInt(int min, int max) {
	    	return min + (int)( Math.random() * ((max - min) + 1) );		// επιστρέφει ένα τυχαίο αριθμό στο διάστημα [min, max].
	    }
	    
	    
	    public PointData rmNearest(double[] x) {				// WORKS.
	    	if ( isEmpty() )
	    		return null;
	    	else if ( first.next == null ) {					// αν υπάρχει μόνο ένα node στη λίστα επέστρεψε αυτό ως το πιο κοντινό και διέγραψε το.
	    		Node temp = first;
	    		first = null;
	    		return new PointData(temp.ptd.x, temp.ptd.y);
	    	}
	    	else {
	    		double min = 1000;								// αρχικοποίηση min με μεγάλο αριθμό, ώστε να μπει σίγουρα την πρώτη φορά.
	    		Node current = first, previous = current, minNode = first;		// minNode = το node με τη μικρότερη απόσταση από το όρισμα x.
	    		
	    		while ( current != null ) {
	    			double distance = current.ptd.dist(x);
	    			// συνθήκη για το minimum, αλλά να μην επιτρέπεται το 0.0 γιατί πρόκειται για την απόσταση από τον εαυτό του.
	    			if ( distance < min && distance != 0.0 ) {
	    				min = distance;							// κάνε το min, την πλέον μικρότερη απόσταση.
	    				minNode = current;						// αποθήκευσε το node με την κοντινότερη απόσταση στο minNode.
	    			}
	    			
	    			current = current.next;
	    		}
	    		
	    		if ( minNode == first )
	    			first = first.next;						// αν μετά από το loop το minNode βρεθεί να είναι το πρώτο, κάνε το δεύτερο το καινούριο first.
	    		else {
	    			while ( previous.next != minNode )		// αλλιώς βρες το προηγούμενο του minNode και
	    				previous = previous.next;
	    			
	    			previous.next = minNode.next;			// κάνε το προηγούμενο του minNode να δείχνει στο επόμενο του minNode.
	    		}
	    		
	    		return new PointData(minNode.ptd.x, minNode.ptd.y);		// επιστρέφει τα data του minNode.
	    	}
	    }
	    
	    
	    public PointList findKNearest(double[] x, int k) {	  // WORKS.
	    	PointList knnlist = new PointList(x.length);	  // knnlist = η λίστα που θα κρατάει τα k κοντινότερα σημεία από το δοθέν σημείο x.
	    	if ( isEmpty() || k >= length() )				  // αν το k είναι μεγαλύτερο από το πλήθος των κόμβων της this.list ή η this.list είναι άδεια τότε την κάνει null.
	    		knnlist = null;
	    	else if ( first.next == null ) {				  // αν υπάρχει μόνο 1 node στη this.list βάλε αυτό το node στην knnlist.
	    		knnlist.append(x, this.first.ptd.y);
	    	}
	    	else {
	    		Node current = first;									// temporary node που θα προχωράει καθώς μπαίνουν τα στοιχεία της this.list στην tempList.
	    		
	    		PointList tempList = new PointList(x.length);			// tempList = προσωρινή λίστα που έχει τα ίδια ακριβώς nodes με την this.list.
    			while ( current != null ) {
    				tempList.append(current.ptd.x, current.ptd.y);		// βάζει ένα - ένα τα στοιχεία της this.list στην tempList.
    				current = current.next;
    			}
    			
		    	for (int i = 0; i < k; i++) {							// κάνε loop k φορές
		    		// αποθηκεύει τα data του node με την μικρότερη απόσταση από το argument x και το διαγράφει από το tempList.
	    			PointData data = tempList.rmNearest(x);
	    			knnlist.append(data.x, data.y);						// προσθέτει στην knnlist τα data του μικρότερου node.
	    		}
	    		
	    	}
	    	return knnlist;												// επιστρέφει την knnlist.
	    }
	    
	    
	    public double classify(double[] x) {		// WORKS.
	    	if ( isEmpty() )
	    		return -1.0;
	    	else {
	    		Node current = first;
	    		
	    		while ( current != null ) {					// διατρέχει όλη τη λίστα.
	    			double distance = current.ptd.dist(x);	// αποθηκεύει σε μια μεταβλητή την απόσταση του argument x με το σημείο που δείχνει το current.
	    			current.score = 1.0 / distance;			// υπολογίζει το 1 / distance για το συγκεκριμένο node ΜΟΝΟ.
	    				
	    				Node temp = first;
	    				
	    				// αρχικοποιούμε ένα node που θα δείχνει στο null αρχικά, και θα δείχνει κάθε φορά στο προηγούμενο node του current με ίδιο y με αυτό.
		    			Node previous = null;
		    			
		    			while ( temp != current ) {				// διατρέχει τη λίστα απ'την αρχή ΜΕΧΡΙ το current.
		    				if ( temp.ptd.y == current.ptd.y )	// αν βρεθεί πριν κάποιο node που να έχει ίδιο y με αυτό του current, το κάνουμε ίσο με το previous.
		    					previous = temp;
		    				temp = temp.next;
		    			}
		    			// όταν βγει από την while το previous θα δείχνει στο προηγούμενο node του current που έχει το ίδιο y.
		    			// πχ. αν το y του current είναι 3.0 το previous θα δείχνει στο προηγούμενο node που θα έχει y = 3.0.
		    			
		    			
		    			
		    			if ( previous != null )
		    				current.score = current.score + previous.score;
		    				// εάν ας πούμε τα y έχουν μια σειρά : 3.0 -> 2.0 -> 3.0.
		    				// Όταν το current γίνει το 2.0 παρ'όλο που υπάρχει κάτι πίσω του , δεν έχει το ίδιο y οπότε δεν θέλουμε να το λάβουμε υπόψιν.
		    				// Επομένως ελέγχει εάν το previous πήρε τιμή διαφορετική από το null μέσα στο while, ΜΟΝΟ εαν έχει πάρει να προσθέσει στο current node
		    				// το score των προηγούμενων. Κάθε φορά το previous του current, θα περιέχει όλα τα score (που είναι ίδιου y)
		    				// από την αρχή μέχρι ΚΑΙ το previous.
		    				// πχ. στο 3.0 -> 2.0 -> 3.0 -> 1.0 -> 3.0 : το τελευταίο 3.0 θα έχει previous το μεσαίο 3.0 και το score αυτού του previous θα είναι το
		    				// 1 / distance του πρώτου 3.0 και του μεσαίου. Οπότε το τελευταίο 3.0 με το παρακάτω assignment παίρνει την τιμή που του έχει δωθεί
		    				// από το current.score = 1.0 / distance παραπάνω + αυτή του previous.
		    				
		       // Αν η συνθήκη previous != null είναι false το previous έχει παραμείνει null μας αρκεί απλά το παραπάνω assignment, δηλαδή : current.score = 1.0 / distance.
	    			
	    			current = current.next;
	    		}
	    		
	    		double maxScore = 0.0;					// θα αποθηκεύει το max score.
	    		double maxY = 0.0;						// θα αποθηκεύει το y με τη μεγαλύτερη βαρύτητα.
	    		Node findmax = first;					// θα σαρώνει τα nodes που το score τους έχει πάρει τιμή.
	    		
	    		while ( findmax != null ) {
    				if ( findmax.score > maxScore ) {		// κλασσικός αλγόριθμος για το max.
    					maxY = findmax.ptd.y;				// δίνουμε το μεγαλύτερο y που βρήκαμε στο maxY.
    					maxScore = findmax.score;			// κάνουμε το max score, το score που ικανοποιεί τη συνθήκη.
    				}
    				findmax = findmax.next;
	    		}
	    		
	    		return maxY;		// επιστρέφει το y με τη μεγαλύτερη βαρύτητα.
	    	}
	    }	
	    
	    
	   /*
     
	    private void display() {		// WORKS.
	    	if ( isEmpty() )
	    		System.out.println("List is empty!");
	    	else {
	    		Node temp = first;
	    		
	    		while( temp != null ) {
	    			System.out.print("[");
	    			for (int i = 0; i < 13; i++)
	    				System.out.print(temp.ptd.x[i]+", ");
	    			System.out.print(temp.ptd.y + "]");
	    			System.out.println();
		    		temp = temp.next;
		        }
	    	}
	    }
      
	    */
	    
	}
