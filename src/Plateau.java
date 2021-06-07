public class Plateau
{
	public static void main(String[] args)
	{
		Dalle[] ensDalles = new Dalle[16];
		
		for(int i=0; i<ensDalles.length; i++)
			ensDalles[i] = new Dalle();
																//ensDalles[0] == dalle A					ensDalles[8]  == dalle I
																//ensDalles[1] == dalle B					ensDalles[9]  == dalle J
																//ensDalles[2] == dalle C					ensDalles[10] == dalle K
																//ensDalles[3] == dalle D					ensDalles[11] == dalle L
																//ensDalles[4] == dalle E					ensDalles[12] == dalle M
																//ensDalles[5] == dalle F					ensDalles[13] == dalle N
																//ensDalles[6] == dalle G					ensDalles[14] == dalle O
																//ensDalles[7] == dalle H					ensDalles[15] == dalle P
		
		//ensDalles[0] == dalle A
		ensDalles[0].ajouterVoisine(4, ensDalles[1]);	//Dalle B	
		ensDalles[0].ajouterVoisine(3, ensDalles[4]);	//Dalle E	
		ensDalles[0].ajouterVoisine(2, ensDalles[2]);	//Dalle C	
		
		//ensDalles[1] == dalle B
		ensDalles[1].ajouterVoisine(4, ensDalles[3]);	//Dalle D
		ensDalles[1].ajouterVoisine(3, ensDalles[7]);	//Dalle H
		ensDalles[1].ajouterVoisine(2, ensDalles[4]);	//Dalle E
		
		//ensDalles[2] == dalle C
		ensDalles[2].ajouterVoisine(4, ensDalles[4]);	//Dalle E
		ensDalles[2].ajouterVoisine(3, ensDalles[8]);	//Dalle I
		ensDalles[2].ajouterVoisine(2, ensDalles[5]);	//Dalle F
		
		//ensDalles[3] == dalle D
		ensDalles[3].ajouterVoisine(4, ensDalles[6]);	//Dalle G
		ensDalles[3].ajouterVoisine(3, ensDalles[10]);	//Dalle K
		ensDalles[3].ajouterVoisine(2, ensDalles[7]);	//Dalle H
		
		//ensDalles[4] == dalle E
		ensDalles[4].ajouterVoisine(4, ensDalles[7]);	//Dalle H
		ensDalles[4].ajouterVoisine(3, ensDalles[11]);	//Dalle L
		ensDalles[4].ajouterVoisine(2, ensDalles[8]);	//Dalle I
		
		//ensDalles[5] == dalle F
		ensDalles[5].ajouterVoisine(4, ensDalles[8]);	//Dalle I
		ensDalles[5].ajouterVoisine(3, ensDalles[12]);	//Dalle M
		ensDalles[5].ajouterVoisine(2, ensDalles[9]);	//Dalle J
		
		//ensDalles[6] == dalle G
		ensDalles[6].ajouterVoisine(2, ensDalles[10]);	//Dalle K
		
		//ensDalles[7] == dalle H
		ensDalles[7].ajouterVoisine(4, ensDalles[10]);	//Dalle K
		ensDalles[7].ajouterVoisine(3, ensDalles[13]);	//Dalle N
		ensDalles[7].ajouterVoisine(2, ensDalles[11]);	//Dalle L
		
		//ensDalles[8] == dalle I
		ensDalles[8].ajouterVoisine(4, ensDalles[11]);	//Dalle L
		ensDalles[8].ajouterVoisine(3, ensDalles[14]);	//Dalle O
		ensDalles[8].ajouterVoisine(2, ensDalles[12]);	//Dalle M
		
		//ensDalles[9] == dalle J
		ensDalles[9].ajouterVoisine(4, ensDalles[12]);	//Dalle M
		
		//ensDalles[10] == dalle K
		ensDalles[10].ajouterVoisine(2, ensDalles[13]);	//Dalle N
		
		//ensDalles[11] == dalle L
		ensDalles[11].ajouterVoisine(4, ensDalles[13]);	//Dalle N
		ensDalles[11].ajouterVoisine(3, ensDalles[15]);	//Dalle P
		ensDalles[11].ajouterVoisine(2, ensDalles[14]);	//Dalle O
		
		//ensDalles[12] == dalle M
		ensDalles[12].ajouterVoisine(4, ensDalles[14]);	//Dalle O
		
		//ensDalles[13] == dalle N
		ensDalles[13].ajouterVoisine(2, ensDalles[15]);	//Dalle P
		
		//ensDalles[14] == dalle O
		ensDalles[14].ajouterVoisine(4, ensDalles[15]);	//Dalle P
		
		//ensDalles[15] == dalle P
		// Rien
		
		for (Dalle d : ensDalles)
			System.out.println(d);
		
		System.out.println();
		
		
		for (Dalle d : ensDalles)
			System.out.println(d.toStringXY());
		
		
	}
}