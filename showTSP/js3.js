var arr = []
var canvas
let cnvs = document.getElementById('canvas')
let mirror = document.getElementById('mirror')
let button = document.getElementById('btn-downlod')
let ctx = cnvs.getContext('2d')
// cnvs.width = mirror.width = window.innerWidth
// cnvs.height = mirror.height = window.innerHeight

if (window.File && window.FileReader && window.FileList && window.Blob) {
  console.log('support File API')

  function readSingleFile (evt) {
    let f = evt.target.files[0]
    if (f) {
      console.log('here')
      let r = new FileReader()
      r.onload = function (e) {
        var contents = e.target.result
        arr = contents.split('\n')
        const data = contents.split('\n').map((coordinate) => {
          const xy = coordinate.split(',')
          return {x: parseInt(xy[1]), y: parseInt(xy[0])}
        })
        //find biggest y and x
        const maxX = data.sort((coordinateA, coordinateB) => coordinateA.x - coordinateB.x).reverse()[0].x
        const maxY = data.sort((coordinateA, coordinateB) => coordinateA.y - coordinateB.y).reverse()[0].y
        cnvs.width = maxX + 100
        cnvs.height = maxY + 100
        drawCanvas()
        checkPoints()
        console.log(canvas)
        /*canvasToImage(canvas)*/
      }
      r.readAsText(f)
    } else {
      console.log('fial to read file')
    }
  }

  button.addEventListener('click', function (e) {
    let dataURL = cnvs.toDataURL('image/jpeg')
    button.href = dataURL
  })
  document.getElementById('fileInput').addEventListener('change', readSingleFile, false)
}

else {
  window.alert('your browser can not support File APIs')
}

function drawCanvas () {
  ctx.lineWidth = 3
  ctx.strokeStyle = '#666666'

  console.log('hello man')
  console.log(arr)

  var points = []
  for (i = 1; i <= arr.length - 1; i++) {
    let cordinat = arr[i - 1].split(',')
    ctx.beginPath()
    ctx.moveTo(cordinat[1], cordinat[0])
    cordinat = arr[i].split(',')
    ctx.lineTo(cordinat[1], cordinat[0])
    ctx.stroke()
  }
}

/*

var t = 1

function animate(points) {
    console.log(points.length)
    if (t < points.length - 1) {
        requestAnimationFrame(animate)
    }
    ctx.beginPath()
    console.log(points[t-1].x)
    ctx.moveTo(points[t - 1].x, points[t - 1].y)
    ctx.lineTo(points[t].x, points[t].y)
    ctx.stroke()
    t++

}
*/

/*
function canvasToImage(canvas) {
    console.log('here')
    let img = new Image()
    let mirror = document.getElementById('mirror')

    img.src = canvas.toDataURL('test.jpg')
}*/

function checkPoints () {
  for (i = 0; i < arr.length - 1; i++) {
    for (j = i + 1; j < arr.length; j++) {
      if (arr[i] === arr[j]) {
        window.alert('erro')
      }
    }
  }
}
