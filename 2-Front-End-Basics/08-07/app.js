const form = document.getElementById('')

const url = 'https://api.themoviedb.org/3/authentication';
const options = {
  method: 'GET',
  headers: {
    accept: 'application/json',
    Authorization: 'Bearer eyJhbGciOiJIUzI1NiJ9.eyJhdWQiOiI5NWNiY2I1YjRmYmY3ZDUzOTgwZWUyZjc2OTY3ODE3NSIsIm5iZiI6MTc1MjAxOTA0OS4wMzAwMDAyLCJzdWIiOiI2ODZkYjA2OTViZWNhZjEyYWQxMDQxNzIiLCJzY29wZXMiOlsiYXBpX3JlYWQiXSwidmVyc2lvbiI6MX0.iqOPJCY8zckKgXFMVhmALu-qcvhbbi9szB4NLuK2ys8'
  }
};

fetch(url, options)
  .then(res => res.json())
  .then(json => console.log(json))
  .catch(err => console.error(err));