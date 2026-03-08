const express = require('express');
const mongoose = require('mongoose');
const cors = require('cors');

const app = express();

app.use(cors());
app.use(express.json());


const uri = "mongodb+srv://wbtabaresc_db_user:Gloria1724.@cluster1.d3wxoga.mongodb.net/?appName=Cluster1"; 

mongoose.connect(uri)
    .then(() => console.log("✅ Conectado exitosamente a MongoDB Atlas"))
    .catch(err => console.error("❌ Error de conexión:", err));

const Producto = mongoose.model('Producto', new mongoose.Schema({
    id_producto: String,
    nombre: String,
    categoria: String,
    unidad_medida: String,
    ubicacion: String,
    proveedor: String,
    descripcion: String
}));

app.post('/api/productos/registrar', async (req, res) => {
    try {
        console.log("Recibiendo datos desde Android:", req.body);
        const nuevoProducto = new Producto(req.body);
        await nuevoProducto.save();
        res.status(201).json({ success: true, message: "¡Producto guardado en Atlas!" });
    } catch (error) {
        res.status(400).json({ success: false, message: error.message });
    }
});

app.get('/api/productos', async (req, res) => {
    try {
        const productos = await Producto.find();
        res.json(productos);
    } catch (error) {
        res.status(500).json({ error: error.message });
    }
});

app.delete('/api/productos/:id', async (req, res) => {
    try {
        await Producto.findByIdAndDelete(req.params.id);
        res.json({ success: true, message: "Producto eliminado" });
    } catch (error) {
        res.status(500).json({ error: error.message });
    }
});
const PORT = 3000;
app.listen(PORT, () => {
    console.log(`🚀 Servidor corriendo en http://localhost:${PORT}`);
});


app.post('/api/login', async (req, res) => {
    const { email, password } = req.body;
    try {

        const usuario = await db.collection('usuarios').findOne({ email, password });
        
        if (usuario) {
            res.json({ success: true, message: "Bienvenido William" });
        } else {
            res.status(401).json({ success: false, message: "Credenciales incorrectas" });
        }
    } catch (error) {
        res.status(500).json({ error: error.message });
    }
});