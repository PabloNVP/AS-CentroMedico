package org.centroMedico.ventana;

import javax.swing.JFrame;

public enum Ventana {
    CONECTAR{
        @Override
        public JFrame crearVentana(){
            return new VentanaConectar();
        }
    },
    INICIO{
        @Override
        public JFrame crearVentana(){
            return new VentanaInicio();
        }
    },
    INGRESO{
        @Override
        public JFrame crearVentana(){
            return new VentanaIngreso();
        }
    }, 
    INGRESO_MEDICO{
        @Override
        public JFrame crearVentana(){
            return new VentanaIngresoMedico();
        }
    }, 
    INGRESO_PACIENTE{
        @Override
        public JFrame crearVentana(){
            return new VentanaIngresoPaciente();
        }
    }, 
    INGRESO_SITUACION{
        @Override
        public JFrame crearVentana(){
            return new VentanaIngresoSituacion();
        }
    },
    INFORMES{
        @Override
        public JFrame crearVentana(){
            return new VentanaInformes();
        }
    }, 
    INFORMES_PACIENTES{
        @Override
        public JFrame crearVentana(){
            return new VentanaInformesPacientesXMedico();
        }
    }, 
    INFORMES_ENFERMEDADES{
        @Override
        public JFrame crearVentana(){
            return new VentanaInformesEnfermedadesXMedico();
        }
    };
    
    public abstract JFrame crearVentana();
}
