import java.util.Random;
import java.awt.Color;
public class Minesweeper{
	
	private int [][] mines;
	private int[][] numbers;
	private final int NUMMINES=10;
	private Random rand;

	
	public Minesweeper()
	{
		this.rand = new Random();
		this.mines = new int[10][2]; //array that contains the mines
		this.numbers = new int[9][9];
		
		int currentMines=1;
		//create the mines in random coordinates
		for(int i=0; i<NUMMINES;i++){
			for(int j=0;j<2;j++){
				this.mines[i][j]= rand.nextInt(9);
			}		
	}
				
		//checks and corrects if there is a repetition
		while(currentMines!=10)	{
			for(int i=0; i<9;i++){
				for(int k=1;k<10;k++){
					if(this.mines[i][0]==this.mines[k][0] && this.mines[i][1]==this.mines[k][1]){
						this.mines[k][0]=this.rand.nextInt(9);
						this.mines[k][1]=this.rand.nextInt(9);
					}
				}				
			}
			currentMines++;
		}
		 this.populateNumbers();
	}	
	
	//Method that counts mines
	public int countMines(int gx, int gy){		
		int cnt =0;
		
		for (int i = gy-1; i <=gy+1 ; i++){			
			for( int j = gx-1 ; j<=gx+1 ; j++){				
				if (this.isMine(j,i)){					
					cnt++;					
//					if ( cnt == 0 ){}				
				}				
			}			
		}		
		return cnt;		
	}	
	public boolean isMine(int x, int y){
		boolean mine = false;
		for (int i=0; i<10; i++){
			if(x==this.mines[i][0] && y==this.mines[i][1]){
				mine=true;
				break;
			}
			else {
				mine=false;
			}
		}
		return mine;
	}		
	//returns the position of the mines
	public int[][] getMines(){
		return this.mines;
	}
	public boolean won(){
		return false;
	}

	public boolean lost(){
		return false;
	}
	
	//Method for array that represents the minesweeper
	 private void populateNumbers(){
	        for (int i=0; i<9;i++){
	            for (int j=0; j<9;j++){
	                if(this.isMine(i, j)){
	                    this.numbers[i][j]=-1;
	                }
	                else{
	                    this.numbers[i][j]= this.countMines(i, j);
	                }
	            }
	        }
	    }

	 //Method that gets numbers
	    public int[][] getNumbers(){
	        return this.numbers;
	    }
	    //Method that fills the empty squares with empty color gray
	    public void chainReaction(int x, int y, MyPanel panel){
	        boolean isNumber1= false;
	        boolean isNumber2= false;
	        boolean isNumber3= false;
	        boolean isNumber4= false;
	                
	        if (countMines(x,y)==0){
	            //For 1
	            for (int i=x; i>0;i--){
	                for(int j=y; j>0; j--){

	                    if (this.numbers[i][j]!=0){
	                        isNumber1=true;
	                        panel.colorArray[i][j] =getColor(i,j);	                        
	                        break;
	                    }
	                    else {
	                        panel.colorArray[i][j] =getColor(i,j);	                    
	                    }
	                }
	                if(isNumber1){
	                    break;
	                }
	            }
	            //For 2
	            for (int i=x; i<9;i++){
	                for(int j=y; j<9; j++ ){
	                    if (this.numbers[i][j]!=0){
	                        isNumber2=true;
	                        panel.colorArray[i][j] =getColor(i,j);
	                        break;
	                    }
	                    else {
	                        panel.colorArray[i][j] =getColor(i,j);
	                    }
	                }
	                if(isNumber2){
	                    break;
	                }
	            }
	            //For 3
	            for (int i=x; i<9;i++){
	                for(int j=y; j>0; j-- ){
	                    if (this.numbers[i][j]!=0){
	                        isNumber3=true;
	                        panel.colorArray[i][j] =getColor(i,j);
	                        break;
	                    }
	                    else {
	                        panel.colorArray[i][j] =getColor(i,j);
	                    }
	                }
	                if(isNumber3){
	                    break;
	                }
	            }
	            //For 4
	            for (int i=x; i>0;i--){
	                for(int j=y; j<9; j++ ){
	                    if (this.numbers[i][j]!=0){
	                        isNumber4=true;
	                        panel.colorArray[i][j] =getColor(i,j);
	                        break;
	                    }
	                    else {
	                        panel.colorArray[i][j] =getColor(i,j);
	                    }
	                }
	                if(isNumber4){
	                    break;
	                }
	            }
	        }
	        else
	        {
	            return;
	        }
	    }
	    
	    // Cases for the colors
	    private Color getColor(int x, int y)
	    {
	        Color newColor=null;
	        switch (this.numbers[x][y]){
	        case 0:
	            newColor = Color.LIGHT_GRAY;
	            break;
	        case 1:
	            newColor = Color.BLUE;
	            break;
	        case 2:
	            newColor = Color.CYAN;
	            break;
	        case 3:
	            newColor = Color.GREEN;
	            break;
	        case 4:
	            newColor = Color.MAGENTA;
	            break;
	        case 5:
	            newColor = Color.ORANGE;
	            break;
	        case 6:
	            newColor = Color.PINK;
	            break;
	        case 7:
	            newColor = Color.YELLOW;
	            break;
	        case 8:
	            newColor = Color.DARK_GRAY;
	            break;	            
	        }
	        return newColor;

	    }
}
