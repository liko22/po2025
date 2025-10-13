public class lab {
	public static void main(String[] args) {
		int i = Integer.parseInt(args[0]);
		for(int x = 0; x<i; x++){
			for(int y = 0; y<i*2;y++) {
				if(y<(i-x) || y > (i+x)) {
					System.out.print(" ");
				}else{
					System.out.print("*");
				}
			}
			System.out.println();
		}
	}
}