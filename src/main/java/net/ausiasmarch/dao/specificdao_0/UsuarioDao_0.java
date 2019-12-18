package net.ausiasmarch.dao.specificdao_0;

import net.ausiasmarch.dao.daointerface.DaoInterface;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import net.ausiasmarch.bean.BeanInterface;
import net.ausiasmarch.bean.UsuarioBean;
import net.ausiasmarch.dao.genericdao.GenericDao;
import net.ausiasmarch.exceptions.CustomException;
import net.ausiasmarch.factory.BeanFactory;
import net.ausiasmarch.helper.Log4jHelper;
import net.ausiasmarch.setting.ConfigurationSettings;

public class UsuarioDao_0 extends GenericDao implements DaoInterface {

    public UsuarioDao_0(Connection oConnection, String ob, UsuarioBean oUsuarioBeanSession) {
        super(oConnection, "usuario", oUsuarioBeanSession);
    }

    public UsuarioBean get(String username, String password) throws Exception, CustomException {
        strSQL += " AND login=?";
        strSQL += " AND password=?";

        UsuarioBean oUsuarioBean;
        ResultSet oResultSet = null;
        PreparedStatement oPreparedStatement = null;
        try {
            oPreparedStatement = oConnection.prepareStatement(strSQL);
            oPreparedStatement.setString(1, username);
            oPreparedStatement.setString(2, password);
            oResultSet = oPreparedStatement.executeQuery();
            if (oResultSet.next()) {
                oUsuarioBean = new UsuarioBean();
                oUsuarioBean.fill(oResultSet, oConnection, ConfigurationSettings.spread, oUsuarioBeanSession);
            } else {
                oUsuarioBean = null;
            }
        } catch (Exception ex) {
            String msg = this.getClass().getName() + ":" + (ex.getStackTrace()[0]).getMethodName() + " ob:" + ob;
            Log4jHelper.errorLog(msg, ex);
            throw new CustomException(500, msg, ex);
        } finally {
            if (oResultSet != null) {
                oResultSet.close();
            }
            if (oPreparedStatement != null) {
                oPreparedStatement.close();
            }
        }
        return oUsuarioBean;
    }

    public UsuarioBean get(String username) throws Exception, CustomException {
        strSQL += " AND login=?";
        UsuarioBean oUsuarioBean;
        ResultSet oResultSet = null;
        PreparedStatement oPreparedStatement = null;
        try {
            oPreparedStatement = oConnection.prepareStatement(strSQL);
            oPreparedStatement.setString(1, username);
            oResultSet = oPreparedStatement.executeQuery();
            if (oResultSet.next()) {
                oUsuarioBean = new UsuarioBean();
                oUsuarioBean.fill(oResultSet, oConnection, ConfigurationSettings.spread, oUsuarioBeanSession);
            } else {
                oUsuarioBean = null;
            }
        } catch (SQLException ex) {
            String msg = this.getClass().getName() + ":" + (ex.getStackTrace()[0]).getMethodName() + " ob:" + ob;
            Log4jHelper.errorLog(msg, ex);
            throw new CustomException(500, msg, ex);
        } finally {
            if (oResultSet != null) {
                oResultSet.close();
            }
            if (oPreparedStatement != null) {
                oPreparedStatement.close();
            }
        }
        return oUsuarioBean;
    }

    public int insert(String email, String username) throws Exception, CustomException {
        PreparedStatement oPreparedStatement = null;
        ResultSet oResultSet = null;
        int iResult = 0;
        try {
            String strsql = "INSERT INTO " + ob + " (login, email, tipo_usuario_id) VALUES (?, ?, 2)";
            oPreparedStatement = oConnection.prepareStatement(strsql, Statement.RETURN_GENERATED_KEYS);
            oPreparedStatement.setString(1, username);
            oPreparedStatement.setString(2, email);
            iResult = oPreparedStatement.executeUpdate();
            oResultSet = oPreparedStatement.getGeneratedKeys();
            oResultSet.next();
            iResult = oResultSet.getInt(1);
        } catch (Exception ex) {
            String msg = this.getClass().getName() + ":" + (ex.getStackTrace()[0]).getMethodName() + " ob:" + ob;
            Log4jHelper.errorLog(msg, ex);
            throw new CustomException(500, msg, ex);
        } finally {
            if (oResultSet != null) {
                oResultSet.close();
            }
            if (oPreparedStatement != null) {
                oPreparedStatement.close();
            }
        }
        return iResult;
    }
}
