/* global Instruccion, INSTRUCCION_INMEDIATO, INSTRUCCION_DIRECTO, INSTRUCCION_INDIRECTO */

var CreacionPrograma = function () {
    this.CAR = "car";
    this.ALM = "alm";
    this.LEE = "lee";
    this.IMP = "imp";
    this.SAL = "sal";
    this.SUM = "sum";
    this.SXI = "sxi";
    this.SXM = "sxm";
    this.RES = "res";
    this.MUL = "mul";
    this.DIV = "div";
    this.FIN = "fin";
    var object = this;
    this.crearPrograma = function (programa) {
        var instrucciones = [];
        var k = 0;
        var lineas = programa.split("\n");
        while (k < lineas.length) {
            var linea = lineas[k];
            if (linea === object.FIN) {
                break;
            }
            var partes = linea.split(" ");
            var comando = partes[0].toLowerCase();
            var param = partes[1];
            var instruccion = null;
            switch (comando) {
                case object.CAR:
                    instruccion = new InstruccionCAR(object.getParametro(param), object.getTipoDireccionamiento(param));
                    instrucciones.push(instruccion);
                    break;
                case object.ALM:
                    instruccion = new InstruccionALM(object.getParametro(param), object.getTipoDireccionamiento(param));
                    instrucciones.push(instruccion);
                    break;
                case object.LEE:
                    instruccion = new InstruccionLEE(object.getParametro(param), object.getTipoDireccionamiento(param));
                    instrucciones.push(instruccion);
                    break;
                case object.IMP:
                    instruccion = new InstruccionIMP(object.getParametro(param), object.getTipoDireccionamiento(param));
                    instrucciones.push(instruccion);
                    break;
                case object.SAL:
                    instruccion = new InstruccionSAL(object.getParametro(param));
                    instrucciones.push(instruccion);
                    break;
                case object.SUM:
                    instruccion = new InstruccionSUM(object.getParametro(param), object.getTipoDireccionamiento(param));
                    instrucciones.push(instruccion);
                    break;
                case object.SXI:
                    instruccion = new InstruccionSXI(object.getParametro(param));
                    instrucciones.push(instruccion);
                    break;
                case object.SXM:
                    instruccion = new InstruccionSXM(object.getParametro(param));
                    instrucciones.push(instruccion);
                    break;
                case object.RES:
                    instruccion = new InstruccionRES(object.getParametro(param), object.getTipoDireccionamiento(param));
                    instrucciones.push(instruccion);
                    break;
                case object.MUL:
                    instruccion = new InstruccionMUL(object.getParametro(param), object.getTipoDireccionamiento(param));
                    instrucciones.push(instruccion);
                    break;
                case object.DIV:
                    instruccion = new InstruccionDIV(object.getParametro(param), object.getTipoDireccionamiento(param));
                    instrucciones.push(instruccion);
                    break;
            }
            k++;
        }
        return instrucciones;
    };

    this.getTipoDireccionamiento = function (param) {
        if (param.length >= 4 && param.charAt(0) === 'R' && param.charAt(1) === '(' && param.charAt(2) === 'R' && param.charAt(3) === '(') {
            return INSTRUCCION_INDIRECTO;
        } else if (param.length >= 2 && param.charAt(0) === 'R' && param.charAt(1) === '(') {
            return INSTRUCCION_DIRECTO;
        } else {
            return INSTRUCCION_INMEDIATO;
        }
    };

    this.getParametro = function (param) {
        var sb = "";
        for (var i = 0; i < param.length; i++) {
            if (param.charAt(i) !== 'R' && param.charAt(i) !== '(' && param.charAt(i) !== ')') {
                sb += param.charAt(i);
            }
        }
        var r = 0;
        r = parseInt(sb);
        if (isNaN(r) || r < 0) {
            r = sb.charAt(0);
        }

        return r;
    };
    return object;
};


var Registro = function (numero) {
    var object = this;
    this.valor = 0;
    this.numero = numero;
    this.getValor = function () {
        var v = parseInt(object.valor);
        if (isNaN(v)) {
            return (v + "").charCodeAt(0);
        } else {
            return v;
        }
    };

    this.setValor = function (valor) {
        object.valor = valor;
    };

    this.getNumero = function () {
        return parseInt(object.numero);
    };

    this.toString = function () {
        return object.numero + ") " + object.valor;
    };
    return object;
};

var Registros = function () {
    var object = this;
    this.registros = [];

    this.crearRegistro = function (numero) {
        var registro = new Registro(numero);
        object.registros.push(registro);
        return registro;
    };

    /**
     * 
     * @param numero es el número del registro.
     * @param valor es el valor del registro.
     * @return true si se pudo establecer el valor en el registro, false en
     * caso contrario.
     */
    this.setRegistro = function (numero, valor) {
        if (numero <= 0) {
            return false;
        }
        var registro = object.getRegistro(numero);
        registro.setValor(valor);
        return true;
    };

    this.getRegistro = function (numero) {
        for (var indiceRegistro in object.registros) {
            var registro = object.registros[indiceRegistro];
            if (registro.getNumero() === numero) {
                return registro;
            }
        }
        return object.crearRegistro(numero);
    };

    this.getRegistros = function () {
        return object.registros;
    };
    return object;
};

var Salida = function () {
    var object = this;
    object.cadena = "";
    this.add = function (cadena) {
        object.cadena += cadena;
    };
    this.toString = function () {
        return object.cadena;
    };
    return object;
};

var RAM = function () {
    var object = this;

    /**
     * Cabezal de lectura.
     */
    this.cl = new Registro(0);
    /**
     * Cabezal de escritura.
     */
    this.ce = new Registro(0);
    /**
     * Registros de datos.
     */
    this.registros = new Registros();
    /**
     * Registro acumulador.
     */
    this.acumulador = new Registro(0);
    /**
     * Contador de programa.
     */
    this.cp = new Registro(-1);
    this.cp.setValor(1);
    ;
    /**
     * Cinta de entrada.
     */
    this.entrada;
    /**
     * Cinta de salida.
     */
    this.salida = new Salida();


    this.ejecutarPrograma = function (instrucciones, mostrar) {
        if (mostrar) {
            console.log("Ejecutando programa");
        }
        try {
            for (; parseInt(object.cp.getValor()) <= instrucciones.length; ) {
                var instruccion = instrucciones[parseInt((object.cp.getValor()) - 1)];
                if (mostrar) {
                    console.log(object.cp.getValor() + ") " + instruccion.mostrar(), object.acumulador);
                }
                instruccion.ejecutar(object.entrada, object.salida, object.cl, object.ce, object.cp, object.acumulador, object.registros);

            }
        } catch (e) {
            alert(e);
        }
        if (mostrar) {
            console.log("Fin programa");
        }
    };

    this.setEntrada = function (entrada) {
        object.entrada = entrada;
    };

    this.getSalida = function () {
        return object.salida;
    };

    this.getCL = function () {
        return object.cl.getValor();
    };

    this.getCE = function () {
        return object.ce.getValor();
    };

    this.getAcumulador = function () {
        return object.acumulador.getValor();
    };

    this.getCP = function () {
        return object.cp.getValor();
    };

    this.getRegistros = function () {
        return object.registros.getRegistros();
    };
};


$(document).ready(function () {

    function crearRegistro(nombreRegistro, valor) {
        return $("<div class='form-group'>" +
                "<label>" + nombreRegistro + "</label>" +
                "<input type='text' class='form-control' value='" + valor + "'disabled='disabled'>" +
                "</div>");
    }

    function ejecutarPrograma(entrada, programa) {
        var ram = new RAM();
        var creacionPrograma = new CreacionPrograma();
        var instrucciones = creacionPrograma.crearPrograma(programa);
        ram.setEntrada(entrada);
        ram.ejecutarPrograma(instrucciones, true);
        $("#cintaSalida").val(ram.getSalida().toString());
        $("#contadorPrograma").val(ram.getCP());
        $("#acumulador").val(ram.getAcumulador());
        var registros = ram.getRegistros();
        $("#otrosRegistros").empty();
        for (var t = 0; t < registros.length; t++) {
            var registro = registros[t];
            var $registro = crearRegistro("R" + registro.getNumero(), registro.getValor());
            $("#otrosRegistros").append($registro);
        }
    }


    $("#btnEjecutar").click(function () {
        var entrada = $("#cintaEntrada").val();
        var programa = $("#programa").val();
        ejecutarPrograma(entrada, programa);
    });

});