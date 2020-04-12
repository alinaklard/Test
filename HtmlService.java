package service;

import dao.ProductDAOImpl;
import data.Product;
import enums.UserType;
import servlet.MainServlet;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.List;

public class HtmlService {
    private static final ConnectionService connectionService = new MySQLConnectionService();

    private static String head(String title, String style) {
        return new StringBuilder()
                .append("<html>")
                .append("  <head>")
                .append("    <title>")
                .append(title)
                .append("    </title>")
                .append("    <link rel='stylesheet' href='cssStyle/")
                .append(style)
                .append("'>")
                .append("  </head>")
                .append("  <body>")
                .toString();
    }

    public static String mainPage(String title, List<Product> products, int type) {
        StringBuilder result = new StringBuilder(new StringBuilder()
                .append(head(title, "MainPageStyle.css"))
                .append("Users on the page:")
                .append(MainServlet.users.size())
                .append(";")
                .append("    <h1>Catalog</h1>")
                .toString());
        for (Product p : products) {
            result.append(new StringBuilder()
                    .append("    <div class='container'>")
                    .append("      <div class='wrapper'>")
                    .append("        <div class='image'>")
                    .append("          <img src='")
                    .append(p.getPicture())
                    .append("          '>")
                    .append("        </div>")
                    .append("        <div class='caption'>")
                    .append("          <div class='caption-top'>")
                    .append("            <h2>")
                    .append(p.getName())
                    .append("            </h2>")
                    .append("            <h2 class = 'price'>$ ")
                    .append(p.getPrice())
                    .append("            </h2>")
                    .append("          </div>")
                    .append("          <div class='char'>")
                    .append("            <ul class='list info-car'>")
                    .append("              <li class='mileage'><img src='https://img.icons8.com/android/24/000000/speedometer.png'/>")
                    .append("                <h4>")
                    .append(p.getMileage())
                    .append(" km,")
                    .append("                </h4>")
                    .append("              </li>")
                    .append("              <li class='petrol'><img src='https://img.icons8.com/android/24/000000/gas-station.png'/>")
                    .append("                <h4>")
                    .append(p.getPetrol())
                    .append(",")
                    .append("                </h4>")
                    .append("              </li>")
                    .append("              <li class='transmission'><img src='https://img.icons8.com/pastel-glyph/64/000000/gearbox.png'/>")
                    .append("                <h4>")
                    .append(p.getTransmission())
                    .append("                </h4>")
                    .append("              </li>")
                    .append("            </ul>")
                    .append("          </div>")
                    .append("          <div class = 'caption-bottom'>")
                    .append("            <p>Seller contacts: ")
                    .append(p.getOwnerNumber())
                    .append("            </p>")
                    .append("            <ul class='list info-owner'>")
                    .append("              <li class='city'>")
                    .append("                <p>")
                    .append(p.getCity())
                    .append("                </p>")
                    .append("              </li>")
                    .append("              <li><p>/</p></li>")
                    .append("              <li class='location'>")
                    .append("                <p>")
                    .append(p.getPlace())
                    .append("                </p>")
                    .append("              </li>")
                    .append("              <li><p>/</p></li>")
                    .append("              <li class='post-date'>")
                    .append("                <p>")
                    .append(p.getDate())
                    .append("                </p>")
                    .append("              </li>")
                    .toString());
            if (type == (UserType.ADMIN.getType())) {
                result.append(new StringBuilder()
                        .append("              <li class='edit'>")
                        .append("                <p>")
                        .append("                  <a class='edit' href='edit_product.jsp?action=view&id=")
                        .append(p.getId())
                        .append("'>Edit")
                        .append("                  </a>")
                        .append("                </p>")
                        .append("              </li>")
                        .toString());
            }
            result.append(new StringBuilder()
                    .append("            </ul>")
                    .append("          </div>")
                    .append("        </div>")
                    .append("      </div>")
                    .append("    </div>")
                    .toString());
        }
        if (type == (UserType.ADMIN.getType())) {
            result.append(new StringBuilder()
                    .append("<br><h3><a class='add' href='add_product.jsp?action=view&id=")
                    .append(products.size() + 1)
                    .append("'>Add product</a></h3>")
                    .toString());
        }
        result.append(new StringBuilder()
                .append("  <body/>")
                .append("</html>")
                .toString());
        return result.toString();
    }

    public static String addPage(String title, int productID) {
        return new StringBuilder()
                .append(head(title, "addEditStyle.css"))
                .append("<div class='container'>")
                .append("  <div class='wrapper'>")
                .append("    <h1>Add product</h1>")
                .append("    <form action='add_product.jsp' method='get'>")
                .append("      <input type='text' name='id' value='")
                .append(productID)
                .append("' readonly><br>")
                .append("      <input type='text' name='name' placeholder='Enter name:' required><br>")
                .append("      <input type='text' name='photo' placeholder='Add photo:' required> <br>")
                .append("      <input type='text' name='price' placeholder='Enter price:' required> <br>")
                .append("      <input type='text' name='mileage' placeholder='Enter mileage:' required> <br>")
                .append("      <input type='text' name='fuel' placeholder='Enter fuel:' required> <br>")
                .append("      <input type='text' name='transmission' placeholder='Enter transmission:' required> <br>")
                .append("      <input type='text' name='owner_number' placeholder='Enter owner`s number:' required> <br>")
                .append("      <input type='text' name='city' placeholder='Enter city:' required> <br>")
                .append("      <input type='text' name='place' placeholder='Enter place:' required> <br>")
                .append("      <input type='submit' value='Submit'/>")
                .append("    </form>")
                .append("  </div>")
                .append("</div>")
                .append("<body/>")
                .append("</html>")
                .toString();
    }

    public static String editPage(String title, int productID) {
        ProductDAOImpl productDAO = new ProductDAOImpl();
        Product p = productDAO.getByID(productID);
        return new StringBuilder()
                .append(head(title, "addEditStyle.css"))
                .append("<div class='container'>")
                .append("  <div class='wrapper'>")
                .append("    <h1>Edit product</h1>")
                .append("    <form action='edit_product.jsp' method='get'>")
                .append("      <div class='input1'>   <p> Enter id:</p><input type='text' name='id' value='")
                .append(productID)
                .append("' readonly></div>")
                .append("      <div class='input1'>   <p> Enter name:</p><input type='text' name='name' value='")
                .append(p.getName())
                .append("' required></div>")
                .append("      <div class='input1'>   <p> Add photo:</p><input type='text' name='photo' value='")
                .append(p.getPicture())
                .append("' required></div>")
                .append("      <div class='input1'>   <p> Enter price:</p><input type='text' name='price' value='")
                .append(p.getPrice())
                .append("' required></div>")
                .append("      <div class='input1'>   <p> Enter mileage:</p><input type='text' name='mileage' value='")
                .append(p.getMileage())
                .append("' required></div>")
                .append("      <div class='input1'>   <p> Enter petrol:</p><input type='text' name='fuel' value='")
                .append(p.getPetrol())
                .append("' required></div>")
                .append("      <div class='input1'>   <p> Enter transmission:</p><input type='text' name='transmission' value='")
                .append(p.getTransmission())
                .append("' required></div>")
                .append("      <div class='input1'>   <p> Enter owner`s number:</p><input type='text' name='owner_number' value='")
                .append(p.getOwnerNumber())
                .append("' required></div>")
                .append("      <div class='input1'>   <p> Enter city:</p><input type='text' name='city' value='")
                .append(p.getCity())
                .append("' required></div>")
                .append("      <div class='input1'>   <p> Enter place:</p><input type='text' name='place' value='")
                .append(p.getPlace())
                .append("' required></div>")
                .append("      <div class='input1'>   <p> Enter post date:</p><input type='text' name='date' value='")
                .append(p.getDate())
                .append("' required></div>")
                .append("      <input class= 'submit' type='submit' value='Submit'/>")
                .append("      <a class='delete' href='edit_product.jsp?action=delete&id=")
                .append(p.getId())
                .append("'>Delete</a>")
                .append("    </form>")
                .append("  </div>")
                .append("</div>")
                .append("  <body/>")
                .append("</html>")
                .toString();
    }
}
