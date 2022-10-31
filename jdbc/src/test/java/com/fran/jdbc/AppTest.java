package com.fran.jdbc;

import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;

import org.junit.Test;

import com.fran.jdbc.utilidades.JdbcUtils;

/**
 * Unit test for simple App.
 */
public class AppTest 
{
    /**
     * Rigorous Test :-)
     */
    @Test
    public void shouldAnswerWithTrue()
    {
    	assertNotNull(JdbcUtils.preparedStatementSelectCompleto("jdbc:postgresql://localhost:5432/prueba", "postgresss", "postgres","Select * from tabla1 where id>? or nombre=?",10,"Frannnn"));
        //assertTrue( true );
    }
}
