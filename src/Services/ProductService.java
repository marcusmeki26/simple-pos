package Services;

import Database.DBConnection;
import Model.ProductModel;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import java.util.ArrayList;

public class ProductService {
    public static ArrayList<ProductModel> getProducts(String queryProduct){
        ArrayList<ProductModel> products = new ArrayList<>();

        try{
            Connection conn = DBConnection.getConnection();
            PreparedStatement pstmt;
            String selectQuery;

            if(queryProduct.equalsIgnoreCase("All Category")){
                selectQuery = "SELECT * FROM products_tbl";
                pstmt = conn.prepareStatement(selectQuery);
            }else{
                selectQuery = "SELECT * FROM products_tbl WHERE CATEGORY = ?";
                pstmt = conn.prepareStatement(selectQuery);
                pstmt.setString(1, queryProduct);
            }

            ResultSet rs = pstmt.executeQuery();

            while(rs.next()){
                products.add(new ProductModel(rs.getString("PRODUCT_NAME"), rs.getString("CATEGORY"), rs.getInt("PRICE"), rs.getString("IMAGE_PATH")));
            }
        }catch (SQLException sqle){
            System.out.println("Unable to Select or Retreive Products");
        }

        return products;
    }
}
