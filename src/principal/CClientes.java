package principal;

import de.wannawork.jcalendar.JCalendarComboBox;
import java.sql.CallableStatement;
import java.sql.*;
import java.sql.SQLException;
import javax.swing.*;
import javax.swing.JOptionPane;
import javax.swing.table.*;
import java.util.Date;
import java.text.SimpleDateFormat;

public class CClientes {

    int id_cliente;
    String dni;
    String nombre;
    String apellido;
    String email;
    String telefono;
    String direccion;
    String fecha_nacimiento;

    public int getId_cliente() {
        return id_cliente;
    }

    public void setId_cliente(int id_cliente) {
        this.id_cliente = id_cliente;
    }

    public String getDni() {
        return dni;
    }

    public void setDni(String dni) {
        this.dni = dni;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getApellido() {
        return apellido;
    }

    public void setApellido(String apellido) {
        this.apellido = apellido;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getTelefono() {
        return telefono;
    }

    public void setTelefono(String telefono) {
        this.telefono = telefono;
    }

    public String getDireccion() {
        return direccion;
    }

    public void setDireccion(String direccion) {
        this.direccion = direccion;
    }

    public String getFecha_nacimiento() {
        return fecha_nacimiento;
    }

    public void setFecha_nacimiento(String fecha_nacimiento) {
        this.fecha_nacimiento = fecha_nacimiento;
    }

    public void InsertarCliente(JTextField txt_nombre,
            JTextField txt_apellido,
            JTextField txt_dni,
            JTextField txt_email,
            JCalendarComboBox txt_nacimiento,
            JTextField txt_telefono,
            JTextField txt_direccion) {

        setNombre(txt_nombre.getText());
        setApellido(txt_apellido.getText());
        setDni(txt_dni.getText());
        setEmail(txt_email.getText());
        
        // Formatear la fecha a un formato compatible con la base de datos
        SimpleDateFormat formatoFecha = new SimpleDateFormat("yyyy-MM-dd"); // Ajusta el formato si es necesario
        String fechaFormateada = formatoFecha.format(txt_nacimiento.getDate());

        setFecha_nacimiento(fechaFormateada);
        setTelefono(txt_telefono.getText());
        setDireccion(txt_direccion.getText());

        CConexion objetoConexion = new CConexion();

        String consulta = ("INSERT INTO clientes(nombre,apellido,dni,email,fecha_nacimiento,telefono,direccion) VALUES (?,?,?,?,?,?,?)");

        try {
            CallableStatement cs = objetoConexion.estableceConexion().prepareCall(consulta);

            cs.setString(1, getNombre());
            cs.setString(2, getApellido());
            cs.setString(3, getDni());
            cs.setString(4, getEmail());
            cs.setString(5, getFecha_nacimiento());
            cs.setString(6, getTelefono());
            cs.setString(7, getDireccion());

            cs.execute();

            JOptionPane.showMessageDialog(null, "Registro Ingresado correctamente");

        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "Hubo un error al ingresar el cliente, error: " + e.toString());

        }

    }

    
    public void MostraClientes(JTable tabla_Clientes, JTextField txt_buscar) {
        CConexion objCon = new CConexion();

        DefaultTableModel modelo = new DefaultTableModel();

        TableRowSorter<TableModel> OrdenarTabla = new TableRowSorter<TableModel>(modelo);

        tabla_Clientes.setRowSorter(OrdenarTabla);

        String sql = "";

        modelo.addColumn("ID");
        modelo.addColumn("NOMBRE");
        modelo.addColumn("APELLIDO");
        modelo.addColumn("DNI");
        modelo.addColumn("EMAIL");
        modelo.addColumn("F.NACIMIENTO");
        modelo.addColumn("TELÉFONO");
        modelo.addColumn("DIRECCION");

        tabla_Clientes.setModel(modelo);

        String searchTerm = txt_buscar.getText(); // Get the search term from the text field

        if (searchTerm.isEmpty()) {
            sql = "SELECT * FROM clientes ORDER BY id_cliente ASC";
        } else {
            sql = "SELECT * FROM clientes WHERE nombre LIKE '%" + searchTerm + "%' OR apellido LIKE '%" + searchTerm + "%' OR dni LIKE '%" + searchTerm + "%' ORDER BY id_cliente ASC";
        }

        String[] datos = new String[8];

        Statement st;

        try {
            st = objCon.estableceConexion().createStatement();
            ResultSet rs = st.executeQuery(sql);

            while (rs.next()) {

                datos[0] = rs.getString(1);
                datos[1] = rs.getString(2);
                datos[2] = rs.getString(3);
                datos[3] = rs.getString(4);
                datos[4] = rs.getString(5);
                datos[5] = rs.getString(6);
                datos[6] = rs.getString(7);
                datos[7] = rs.getString(8);

                modelo.addRow(datos);
            }
            tabla_Clientes.setModel(modelo);

        } catch (Exception e) {

            JOptionPane.showMessageDialog(null, "Hubo un error al mostrar la lista de cliente, error: " + e.toString());

        }
    }

    public void SeleccionarCliente(JTable tabla_clientes,
            JTextField txt_id,
            JTextField txt_nombre,
            JTextField txt_apellido,
            JTextField txt_dni,
            JTextField txt_email,
            JCalendarComboBox txt_nacimiento,
            JTextField txt_telefono,
            JTextField txt_direccion
    ) {

        try {
            int fila = tabla_clientes.getSelectedRow();

            if (fila >= 0) {

                txt_id.setText(tabla_clientes.getValueAt(fila, 0).toString());
                txt_nombre.setText(tabla_clientes.getValueAt(fila, 1).toString());
                txt_apellido.setText(tabla_clientes.getValueAt(fila, 2).toString());
                txt_dni.setText(tabla_clientes.getValueAt(fila, 3).toString());
                txt_email.setText(tabla_clientes.getValueAt(fila, 4).toString());

                // Conversión de String a Date
                String fechaNacimientoStr = tabla_clientes.getValueAt(fila, 5).toString();
                SimpleDateFormat formatoFecha = new SimpleDateFormat("yyyy-MM-dd"); // Ajustar formato según sea necesario
                Date fechaNacimiento = formatoFecha.parse(fechaNacimientoStr);

                // Asignar la fecha al JCalendarComboBox
                txt_nacimiento.setDate(fechaNacimiento);

                // Asignación de los otros valores
                txt_telefono.setText(tabla_clientes.getValueAt(fila, 6).toString());
                txt_direccion.setText(tabla_clientes.getValueAt(fila, 7).toString());

            } else {
                JOptionPane.showMessageDialog(null, "Fila no seleccionada");
            }

        } catch (Exception e) {

            JOptionPane.showMessageDialog(null, "Error de selección, error: " + e.toString());

        }
    }

    public void ModificarCliente(JTextField txt_id,
            JTextField txt_nombre,
            JTextField txt_apellido,
            JTextField txt_dni,
            JTextField txt_email,
            JCalendarComboBox txt_nacimiento,
            JTextField txt_telefono,
            JTextField txt_direccion
    ) {

        setId_cliente(Integer.parseInt(txt_id.getText()));
        setNombre(txt_nombre.getText());
        setApellido(txt_apellido.getText());
        setDni(txt_dni.getText());
        setEmail(txt_email.getText());

        // Formatear la fecha a un formato compatible con la base de datos
        SimpleDateFormat formatoFecha = new SimpleDateFormat("yyyy-MM-dd"); // Ajusta el formato si es necesario
        String fechaFormateada = formatoFecha.format(txt_nacimiento.getDate());

        setFecha_nacimiento(fechaFormateada);
        setTelefono(txt_telefono.getText());
        setDireccion(txt_direccion.getText());

        CConexion objetoConexion = new CConexion();

        String consulta = ("UPDATE clientes "
                + "SET nombre = ?, apellido = ?,dni = ?, email = ?, fecha_nacimiento = ?,telefono = ?, direccion = ?"
                + "WHERE id_cliente = ?");

        try {
            CallableStatement cs = objetoConexion.estableceConexion().prepareCall(consulta);

            cs.setString(1, getNombre());
            cs.setString(2, getApellido());
            cs.setString(3, getDni());
            cs.setString(4, getEmail());
            cs.setString(5, getFecha_nacimiento());
            cs.setString(6, getTelefono());
            cs.setString(7, getDireccion());
            cs.setInt(8, getId_cliente());

            cs.execute();

            JOptionPane.showMessageDialog(null, "Registro Actualizado correctamente!!");

        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "Hubo un error al actualizar el registro, error: " + e.toString());

        }

    }

    public void EliminarCliente(JTextField txt_id) {

        setId_cliente(Integer.parseInt(txt_id.getText()));

        CConexion objetoConexion = new CConexion();

        String consulta = ("DELETE FROM clientes WHERE id_cliente = ?");

        try {
            CallableStatement cs = objetoConexion.estableceConexion().prepareCall(consulta);

            cs.setInt(1, getId_cliente());

            cs.execute();

            JOptionPane.showMessageDialog(null, "Registro Eliminado correctamente!!");

        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "Hubo un error al eliminar el registro, error: " + e.toString());

        }
    }
    
        public void ActualizarEstadoCliente(JTextField txt_id) {

        setId_cliente(Integer.parseInt(txt_id.getText()));

        CConexion objetoConexion = new CConexion();

        String consulta = ("UPDATE clientes SET estado= 'I' WHERE id_cliente = ?");

        try {
            CallableStatement cs = objetoConexion.estableceConexion().prepareCall(consulta);

            cs.setInt(1, getId_cliente());

            cs.execute();

            JOptionPane.showMessageDialog(null, "Registro Eliminado correctamente!!");

        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "Hubo un error al eliminar el registro, error: " + e.toString());

        }
    }

    public void NuevoCliente(JTextField txt_id,
            JTextField txt_nombre,
            JTextField txt_apellido,
            JTextField txt_dni,
            JTextField txt_email,
            JCalendarComboBox txt_nacimiento,
            JTextField txt_telefono,
            JTextField txt_direccion
    ) {
        txt_id.setText("");
        txt_nombre.setText("");
        txt_apellido.setText("");
        txt_dni.setText("");
        txt_email.setText("");
        Date currentDate = new Date();  // Puedes usar cualquier fecha
        txt_nacimiento.setDate(currentDate);
        txt_telefono.setText("");
        txt_direccion.setText("");
        
        txt_nombre.requestFocusInWindow();

    }

    public void MostrarUltimoCliente(JTable tabla_Clientes, JTextField txt_buscar) {
        CConexion objCon = new CConexion();

        DefaultTableModel modelo = new DefaultTableModel();

        TableRowSorter<TableModel> OrdenarTabla = new TableRowSorter<TableModel>(modelo);

        tabla_Clientes.setRowSorter(OrdenarTabla);

        String sql = " ";

        modelo.addColumn("ID");
        modelo.addColumn("NOMBRE");
        modelo.addColumn("APELLIDO");
        modelo.addColumn("DNI");
        modelo.addColumn("EMAIL");
        modelo.addColumn("F.NACIMIENTO");
        modelo.addColumn("TELÉFONO");
        modelo.addColumn("DIRECCION");

        tabla_Clientes.setModel(modelo);

        String searchTerm = txt_buscar.getText(); // Get the search term from the text field

        if (searchTerm.isEmpty()) {
            sql = "SELECT * FROM clientes ORDER BY updated_at DESC LIMIT 1;";
        } else {
            sql = "SELECT * FROM clientes WHERE nombre LIKE '%" + searchTerm + "%' OR apellido LIKE '%" + searchTerm + "%' OR dni LIKE '%" + searchTerm + "%' ORDER BY id_cliente ASC";
        }

        String[] datos = new String[8];

        Statement st;

        try {
            st = objCon.estableceConexion().createStatement();
            ResultSet rs = st.executeQuery(sql);

            while (rs.next()) {

                datos[0] = rs.getString(1);
                datos[1] = rs.getString(2);
                datos[2] = rs.getString(3);
                datos[3] = rs.getString(4);
                datos[4] = rs.getString(5);
                datos[5] = rs.getString(6);
                datos[6] = rs.getString(7);
                datos[7] = rs.getString(8);

                modelo.addRow(datos);
            }
            tabla_Clientes.setModel(modelo);

        } catch (Exception e) {

            JOptionPane.showMessageDialog(null, "Hubo un error al mostrar la lista de cliente, error: " + e.toString());

        }
    }
    
    
}
