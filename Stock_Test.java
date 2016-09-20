import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.sql.Timestamp;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.Map.Entry;


public class Stock_Test {
	
	public static String COMMON = "Common";
	public static String PREFERRED = "Preferred";
	
	public static void main(String[] args) {
		
		Map<String, String> stocksHeader = new HashMap<String, String>();
		Map<String, String> stockRecords = new HashMap<String, String>();
		Map<String, String> stockTransaction = new HashMap<String, String>();
		BufferedReader br = null;
		stocksHeader.put("stocks_Header", "Stock_Symbol,Type,Last_Dividend,Fixed_Dividend,Per_Value");
		stockRecords.put("TEA", "TEA,Common,0,-,100");
		stockRecords.put("POP","POP,Common,8,-,100");
		stockRecords.put("ALE","ALE,Common,23,-,60");
		stockRecords.put("GIN","GIN,Preferred,8,0.02,100");
		stockRecords.put("JOE","JOE,Common,13,-,250");
		
		System.out.println("*********************************");
    	System.out.println("1. Dividend Yield");
    	System.out.println("2. P/E Ratio");
    	System.out.println("3. Geometric Mean");
    	System.out.println("4. Volume Weighted Stock Price");
    	System.out.println("5. Stock Transations");
    	System.out.println("Enter q for Exit");
    	System.out.println("*********************************");
    	while(true)
    	{
	        try {
	        	
	        	System.out.println("Please select the Option(1-4) / Exit - q : ");
	        	
	            br = new BufferedReader(new InputStreamReader(System.in));
	            String input = br.readLine();
	            if(input.equals("1"))
	            {	 
	            	Map<String, Double> stocksValue = new HashMap<String, Double>();
	            	System.out.println("1. Dividend Yield");
	            	System.out.println("Please enter price value for the Stocks");
	            	for(Entry<String, String> key : stockRecords.entrySet())
	            	{
	            		System.out.print(key.getKey()+" : ");
	            		String value = br.readLine();
	            		
	            		if(value.equalsIgnoreCase("") || value==null || isDouble(value))
	            		{
	            			value = "0";
	            		}
	            		stocksValue.put(key.getKey(), Double.parseDouble(value));	            		
	            	}
//	            	dividend_Yield(stockRecords,stocksValue);
	            	dividend_Yield(input,stockRecords,stocksValue);
	            }
	            else if(input.equals("2"))
	            {
	            	Map<String, Double> stocksValue = new HashMap<String, Double>();
	            	System.out.println("2. P/E Ratio");
	            	System.out.println("Please enter price value for the Stocks");
	            	for(Entry<String, String> key : stockRecords.entrySet())
	            	{
	            		System.out.print(key.getKey()+" : ");
	            		String value = br.readLine();
	            		if(value.equalsIgnoreCase("") || value==null || isDouble(value))
	            		{
	            			value = "0";
	            		}
	            		
	            		stocksValue.put(key.getKey(), Double.parseDouble(value));
	            	}
//	            	peRatio(stockRecords,stocksValue);
	            	dividend_Yield(input,stockRecords,stocksValue);
	            }
	            else if(input.equals("3"))
	            {
	            	Map<String, Double> stocksValue = new HashMap<String, Double>();
	            	System.out.println("3. Geometric Mean");
	            	System.out.println("Please enter price value for the Stocks");
	            	for(Entry<String, String> key : stockRecords.entrySet())
	            	{
	            		System.out.print(key.getKey()+" : ");
	            		String value = br.readLine();
	            		if(value.equalsIgnoreCase("") || value==null || isDouble(value))
	            		{
	            			value = "0";
	            		}
	            		
	            		stocksValue.put(key.getKey(), Double.parseDouble(value));
	            	}
//	            	peRatio(stockRecords,stocksValue);
	            	geometric_Mean(stocksValue);
	            
	            	
	            }
	            else if(input.equals("5"))
	            {
	            	Map<String, Double> stocksValue = new HashMap<String, Double>();
	            	System.out.println("2. P/E Ratio");
	            	System.out.println("Please enter price value for the Stocks");
	            	for(Entry<String, String> key : stockRecords.entrySet())
	            	{
	            		System.out.print(key.getKey()+" : ");
	            		String value = br.readLine();
	            		if(value.equalsIgnoreCase("") || value==null || isDouble(value))
	            		{
	            			value = "0";
	            		}
	            		
	            		stocksValue.put(key.getKey(), Double.parseDouble(value));
	            	}
//	            	peRatio(stockRecords,stocksValue);
	            	dividend_Yield(input,stockRecords,stocksValue);
	            }
	            else if(input.equalsIgnoreCase("q"))
	            {
	            	System.exit(0);
	            }
	        } catch (IOException e) {
	        	e.printStackTrace();
	        }
    	}
	}

	
	public static void dividend_Yield(String input,Map<String, String> stockRecords,Map<String, Double> stocksValue)
	{
		Map<String, String> stockTransaction = new HashMap<String, String>();
		for(Entry<String, String> key : stockRecords.entrySet())
		{
			String[] sValue= key.getValue().split(",");
			
			String Stock_Symbol = sValue[0];
			String Type = sValue[1];
			Double Last_Dividend = sValue[2].equalsIgnoreCase("-")? 0F : Double.parseDouble(sValue[2]);
			Double Fixed_Dividend = sValue[3].equalsIgnoreCase("-")? 0F : Double.parseDouble(sValue[3]);			
			Double Per_Value = sValue[4].equalsIgnoreCase("-")? 0F : Double.parseDouble(sValue[4]);
			Double ft = 0.0;
			if(input.equals("1"))
			{
				if(Type.equalsIgnoreCase(COMMON))
				{
					ft = Last_Dividend / stocksValue.get(Stock_Symbol);
				}
				else if(Type.equalsIgnoreCase(PREFERRED))
				{
					ft = (Fixed_Dividend * Per_Value) / stocksValue.get(Stock_Symbol);
				}
			}
			else if(input.equals("2"))
			{			
				if(Type.equalsIgnoreCase(COMMON))
				{
					ft = stocksValue.get(Stock_Symbol) / Last_Dividend;
				}
				else if(Type.equalsIgnoreCase(PREFERRED))
				{
					ft = stocksValue.get(Stock_Symbol) / (Fixed_Dividend * Per_Value);
				}
			}
			System.out.println(Stock_Symbol+" : "+(ft.isNaN() || ft.isInfinite()? 0F : ft));
			
			Date d = new Date();
			String transaction_Value = key.getKey()+","+new Timestamp(d.getTime())+",-,-,"+stocksValue.get(Stock_Symbol); 
			stockTransaction.put(key.getKey(), transaction_Value);
			
		}		
		
	}
	
	
	/*public static void peRatio(Map<String, String> stockRecords,Map<String, Double> stocksValue)
	{
		for(Entry<String, String> key : stockRecords.entrySet())
		{
			String[] sValue= key.getValue().split(",");
			
			String Stock_Symbol = sValue[0];
			String Type = sValue[1];
			Double Last_Dividend = sValue[2].equalsIgnoreCase("-")? 0F : Double.parseDouble(sValue[2]);
			Double Fixed_Dividend = sValue[3].equalsIgnoreCase("-")? 0F : Double.parseDouble(sValue[3]);			
			Double Per_Value = sValue[4].equalsIgnoreCase("-")? 0F : Double.parseDouble(sValue[4]);
			Double ft = 0f;
			
			if(Type.equalsIgnoreCase(COMMON))
			{
				ft = stocksValue.get(Stock_Symbol) / Last_Dividend;
			}
			else if(Type.equalsIgnoreCase(PREFERRED))
			{
				ft = stocksValue.get(Stock_Symbol) / (Fixed_Dividend * Per_Value);
			}
			
			System.out.println(Stock_Symbol+" : "+(ft.isNaN() || ft.isInfinite()? 0F : ft));
		}		
		
	}*/
	
	public static void geometric_Mean(Map<String, Double> stocksValue)
	{
		double sum = 1.0;
		
		for(Entry<String, Double> key : stocksValue.entrySet())
		{
			sum = sum * key.getValue();
		}
		double geoMean = Math.pow(sum, 1.0/stocksValue.size());
		System.out.println("geometric mean of prices for all stocks");
		System.out.println(geoMean);

	}
	
	public static boolean isDouble(String str)
	{
		try{
			Double f = Double.parseDouble(str);
		}catch (NumberFormatException  e) {
			return true;
		}
		return false;
	}
}




