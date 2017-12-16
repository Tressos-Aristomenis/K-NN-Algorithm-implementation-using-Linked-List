class PointData {
	    public double[] x;
	    public double y;
	
	    public PointData(double[] x, double y) {
	    	this.x = x.clone();
	    	this.y = y;
	    }
	
	    public double dist(double[] x) {						// WORKS.
	    	double res = 0.0;
	    	for (int i = 0; i < x.length; i++)
	    		res = res + Math.pow( this.x[i] - x[i], 2 );
          // το this.x[i] αφορά το σημείο της λίστας που καλεί την dist(), το x[i] είναι argument της dist.
	    															
	    	return Math.sqrt(res);								// επιστρέφει τη ρίζα της απόστασης.
	    }
}
