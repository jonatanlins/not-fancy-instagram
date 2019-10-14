import axios from 'axios';

export const baseURL = 'http://localhost:5000/';

export const api = axios.create({ baseURL });

export default api;
