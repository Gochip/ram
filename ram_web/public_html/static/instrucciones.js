/* global Instruccion */

var INSTRUCCION_INMEDIATO = 1;
var INSTRUCCION_DIRECTO = 2;
var INSTRUCCION_INDIRECTO = 3;

var Instruccion = (function () {
    var instancia = this;
    this.ejecutar = function (entrada, salida, cl, ce, cp, acum, registros) {};
    this.mostrar = function () {};
    return instancia;
});

InstruccionALM.prototype = new Instruccion();
function InstruccionALM(n, direccionamiento) {
    Instruccion.call(this);
    var object = this;
    this.n = n;
    this.direccionamiento = direccionamiento;

    this.ejecutar = function (entrada, salida, cl, ce, cp, acum, registros) {
        switch (object.direccionamiento) {
            case INSTRUCCION_INDIRECTO:
                var v = registros.getRegistro(object.n).getValor();
                registros.setRegistro(v, acum.getValor());
                break;
            default:
                registros.setRegistro(n, acum.getValor());
        }
        cp.setValor(cp.getValor() + 1);
    };

    this.mostrar = function () {
        switch (object.direccionamiento) {
            case INSTRUCCION_INDIRECTO:
                return "ALM R(R(" + n + "))";
            default:
                return "ALM R(" + n + ")";
        }
    };
    return object;
}

InstruccionCAR.prototype = new Instruccion();
function InstruccionCAR(n, direccionamiento) {
    Instruccion.call(this);
    var object = this;
    this.n = n;
    this.direccionamiento = direccionamiento;

    this.ejecutar = function (entrada, salida, cl, ce, cp, acum, registros) {
        switch (object.direccionamiento) {
            case INSTRUCCION_DIRECTO:
                acum.setValor(registros.getRegistro(object.n).getValor());
                break;
            case INSTRUCCION_INDIRECTO:
                var v = registros.getRegistro(object.n).getValor();
                acum.setValor(registros.getRegistro(v).getValor());
                break;
            default:
                acum.setValor(object.n);
        }
        cp.setValor(cp.getValor() + 1);
    };

    this.mostrar = function () {
        switch (object.direccionamiento) {
            case INSTRUCCION_DIRECTO:
                return "CAR R(" + n + ")";
            case INSTRUCCION_INDIRECTO:
                return "CAR R(R(" + n + "))";
            default:
                return "CAR " + n;
        }
    };
    return object;
}

InstruccionDIV.prototype = new Instruccion();
function InstruccionDIV(n, direccionamiento) {
    Instruccion.call(this);
    var object = this;
    this.n = n;
    this.direccionamiento = direccionamiento;

    this.ejecutar = function (entrada, salida, cl, ce, cp, acum, registros) {
        switch (object.direccionamiento) {
            case INSTRUCCION_DIRECTO:
                var v = registros.getRegistro(object.n).getValor();
                if (v == 0) {
                    acum.setValor(0);
                } else {
                    acum.setValor(acum.getValor() / v);
                }
                break;
            case INSTRUCCION_INDIRECTO:
                var reg = registros.getRegistro(object.n).getValor();
                var valor = registros.getRegistro(reg).getValor();
                if (valor == 0) {
                    acum.setValor(0);
                } else {
                    acum.setValor(acum.getValor() / valor);
                }
                break;
            default:
                if (n == 0) {
                    acum.setValor(0);
                } else {
                    acum.setValor(acum.getValor() / n);
                }
        }
        cp.setValor(cp.getValor() + 1);
    };

    this.mostrar = function () {
        switch (object.direccionamiento) {
            case INSTRUCCION_DIRECTO:
                return "SUM R(" + n + ")";
            case INSTRUCCION_INDIRECTO:
                return "SUM R(R(" + n + "))";
            default:
                return "SUM " + n;
        }
    };
    return object;
}

InstruccionIMP.prototype = new Instruccion();
function InstruccionIMP(n, direccionamiento) {
    Instruccion.call(this);
    var object = this;
    this.n = n;
    this.direccionamiento = direccionamiento;

    this.ejecutar = function (entrada, salida, cl, ce, cp, acum, registros) {
        switch (object.direccionamiento) {
            case INSTRUCCION_INDIRECTO:
                var v = registros.getRegistro(object.n).getValor();
                salida.add(registros.getRegistro(v).getValor());
                break;
            case INSTRUCCION_DIRECTO:
                var va = registros.getRegistro(object.n).getValor();
                salida.add(va);
                break;
            default:
                salida.add(n);
        }
        cp.setValor(cp.getValor() + 1);
        ce.setValor(ce.getValor() + 1);
        salida.add("|");
    };

    this.mostrar = function () {
        switch (object.direccionamiento) {
            case INSTRUCCION_INDIRECTO:
                return "IMP R(R(" + n + "))";
            case INSTRUCCION_DIRECTO:
                return "IMP R(" + n + ")";
            default:
                return "IMP " + n;
        }
    };
    return object;
}

InstruccionLEE.prototype = new Instruccion();
function InstruccionLEE(n, direccionamiento) {
    Instruccion.call(this);
    var object = this;
    this.n = n;
    this.direccionamiento = direccionamiento;

    this.ejecutar = function (entrada, salida, cl, ce, cp, acum, registros) {
        var c = null;
        if (cl.getValor() >= entrada.length) {
            throw new Error("ERROR, se acabó la entrada");
        } else {
            c = entrada.charAt(cl.getValor());
        }
        var cf = parseInt(c + "");
        switch (object.direccionamiento) {
            case INSTRUCCION_INDIRECTO:
                var v = registros.getRegistro(object.n).getValor();
                registros.setRegistro(v, cf);
                break;
            default:
                registros.setRegistro(object.n, cf);
        }
        cp.setValor(cp.getValor() + 1);
        cl.setValor(cl.getValor() + 1);
    };

    this.mostrar = function () {
        switch (object.direccionamiento) {
            case INSTRUCCION_INDIRECTO:
                return "LEE R(R(" + object.n + "))";
            default:
                return "LEE R(" + object.n + ")";
        }
    };
    return object;
}

InstruccionMUL.prototype = new Instruccion();
function InstruccionMUL(n, direccionamiento) {
    Instruccion.call(this);
    var object = this;
    this.n = n;
    this.direccionamiento = direccionamiento;

    this.ejecutar = function (entrada, salida, cl, ce, cp, acum, registros) {
        switch (object.direccionamiento) {
            case INSTRUCCION_DIRECTO:
                acum.setValor(acum.getValor() * registros.getRegistro(object.n).getValor());
                break;
            case INSTRUCCION_INDIRECTO:
                var v = registros.getRegistro(object.n).getValor();
                acum.setValor(acum.getValor() * registros.getRegistro(v).getValor());
                break;
            default:
                acum.setValor(acum.getValor() * object.n);
        }
        cp.setValor(cp.getValor() + 1);
    };

    this.mostrar = function () {
        switch (object.direccionamiento) {
            case INSTRUCCION_DIRECTO:
                return "SUM R(" + n + ")";
            case INSTRUCCION_INDIRECTO:
                return "SUM R(R(" + n + "))";
            default:
                return "SUM " + n;
        }
    };
    return object;
}
;


InstruccionRES.prototype = new Instruccion();
function InstruccionRES(n, direccionamiento) {
    Instruccion.call(this);
    var object = this;
    this.n = n;
    this.direccionamiento = direccionamiento;

    this.ejecutar = function (entrada, salida, cl, ce, cp, acum, registros) {
        switch (object.direccionamiento) {
            case INSTRUCCION_DIRECTO:
                acum.setValor(Math.max(0, acum.getValor() - registros.getRegistro(object.n).getValor()));
                break;
            case INSTRUCCION_INDIRECTO:
                var v = registros.getRegistro(object.n).getValor();
                acum.setValor(Math.max(0, acum.getValor() - registros.getRegistro(v).getValor()));
                break;
            default:
                acum.setValor(Math.max(0, acum.getValor() - n));
        }
        cp.setValor(cp.getValor() + 1);
    };

    this.mostrar = function () {
        switch (object.direccionamiento) {
            case INSTRUCCION_DIRECTO:
                return "SUM R(" + n + ")";
            case INSTRUCCION_INDIRECTO:
                return "SUM R(R(" + n + "))";
            default:
                return "SUM " + n;
        }
    };
    return object;
}
;

InstruccionSAL.prototype = new Instruccion();
function InstruccionSAL(m) {
    Instruccion.call(this);
    var object = this;
    this.m = m;

    this.ejecutar = function (entrada, salida, cl, ce, cp, acum, registros) {
        cp.setValor(object.m);
    };

    this.mostrar = function () {
        return "SAL " + object.m;
    };
    return object;
}
InstruccionSI.prototype = new Instruccion();
function InstruccionSI(m) {
    Instruccion.call(this);
    var object = this;
    this.m = m;

    this.ejecutar = function (entrada, salida, cl, ce, cp, acum, registros) {
    };

    this.mostrar = function () {
        return "";
    };
    return object;
}

InstruccionSUM.prototype = new Instruccion();
function InstruccionSUM(n, direccionamiento) {
    Instruccion.call(this);
    var object = this;
    this.n = n;
    this.direccionamiento = direccionamiento;

    this.ejecutar = function (entrada, salida, cl, ce, cp, acum, registros) {
        switch (object.direccionamiento) {
            case INSTRUCCION_DIRECTO:
                acum.setValor(acum.getValor() + registros.getRegistro(object.n).getValor());
                break;
            case INSTRUCCION_INDIRECTO:
                var v = registros.getRegistro(object.n).getValor();
                acum.setValor(acum.getValor() + registros.getRegistro(v).getValor());
                break;
            default:
                acum.setValor(acum.getValor() + n);
        }
        cp.setValor(cp.getValor() + 1);
    };

    this.mostrar = function () {
        switch (object.direccionamiento) {
            case INSTRUCCION_DIRECTO:
                return "SUM R(" + n + ")";
            case INSTRUCCION_INDIRECTO:
                return "SUM R(R(" + n + "))";
            default:
                return "SUM " + n;
        }
    };
    return object;
}

InstruccionSXI.prototype = new Instruccion();
function InstruccionSXI(m) {
    Instruccion.call(this);
    var object = this;
    this.m = m;

    this.ejecutar = function (entrada, salida, cl, ce, cp, acum, registros) {
        if (acum.getValor() == 0) {
            cp.setValor(object.m);
        } else {
            cp.setValor(cp.getValor() + 1);
        }
    };

    this.mostrar = function () {
        return "SxI " + object.m;
    };
    return object;
}

InstruccionSXM.prototype = new Instruccion();
function InstruccionSXM(m) {
    Instruccion.call(this);
    var object = this;
    this.m = m;

    this.ejecutar = function (entrada, salida, cl, ce, cp, acum, registros) {
        if (acum.getValor() > 0) {
            cp.setValor(object.m);
        } else {
            cp.setValor(cp.getValor() + 1);
        }
    };

    this.mostrar = function () {
        return "SxM " + object.m;
    };
    return object;
}