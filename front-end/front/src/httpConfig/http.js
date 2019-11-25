import axios from 'axios'
import apiURL from './api.js'
// import QS from 'qs'
// import cookie from '../../static/js/cookie.js'
import {Message} from 'element-ui'

axios.defaults.timeout = 10000
axios.defaults.baseURL = apiURL
axios.defaults.transformRequest = [
  function (data) {
    let ret = ''
    for (let it in data) {
      ret += encodeURIComponent(it) + '=' + encodeURIComponent(data[it]) + '&'
    }
    return ret
  }
]
// 路由请求拦截
// http request 拦截器
axios.interceptors.request.use(
  config => {
    // // config.data = JSON.stringify(config.data);
    // // config.headers['Content-Type'] = 'application/json;charset=UTF-8'
    // // 判断是否存在ticket，如果存在的话，则每个http header都加上ticket
    // /* if (cookie.get('token')) {
    //   // 用户每次操作，都将cookie设置成2小时
    //   cookie.set('token', cookie.get('token'), 1 / 12)
    //   cookie.set('name', cookie.get('name'), 1 / 12)
    //   config.headers.token = cookie.get('token')
    //   config.headers.name = cookie.get('name')
    // } */
    return config
  },
  error => {
    Message.error({message: '请求超时'})
    return Promise.reject(error.response)
  })
// 路由响应拦截
// http response 拦截器
axios.interceptors.response.use(
  response => {
    if (response.data.resultCode === '404') {
      console.log('response.data.resultCode是404')
    } else {
      return Promise.resolve(response)
    }
  },
  error => {
    return Promise.reject(error.response) // 返回接口返回的错误信息
  })
export default axios

export function get (url, params) {
  return new Promise((resolve, reject) => {
    axios.get(apiURL, {
      params: params
    }).then(res => {
      resolve(res.data)
    }).catch(err => {
      reject(err.data)
    })
  })
}
export const getRequest = (url) => {
  return axios({
    method: 'get',
    url: `${apiURL}${url}`
  })
}
