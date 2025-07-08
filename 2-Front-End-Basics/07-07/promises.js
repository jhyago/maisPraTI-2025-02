function putWaterToBoil() {
    console.log('Boil the water.')
    return new Promise((resolve, reject) => {
        setTimeout(() => {
            console.log('We have boiled water')
            resolve()
        }, 5000)
    })
}

function getReadyToMakeCoffee() {
    console.log('Grind coffee beans.')
    console.log('Line the basket of your coffee maker with a filter.')
    console.log('Wet the filter, and let it drain into your cup.')
    console.log('Discard the wather in the cup.')
    console.log('Measure the ground coffee into the wet filter.')
}

function makeCoffee() {
    console.log('Pour water to wet the ground beans and drain into your cup.')
}

// putWaterToBoil()
// .then(getReadyToMakeCoffee)
// .then(makeCoffee)
// .then(() => {
//     console.log('Your coffee is ready!')
// })
// .catch((error) => {
//     console.error('Something went wrong: ', error)
// })

// Promise.all([putWaterToBoil(), Promise.resolve(getReadyToMakeCoffee())])
// .then(makeCoffee)
// .then(() => {
//     console.log('Your coffee is ready!')
// })

async function makeCoffeeRoutine() {
    try {
        await putWaterToBoil()
        getReadyToMakeCoffee()
        makeCoffee()
        console.log('Your coffee is ready!')
    } catch (error) {
        console.error('Something went wrong: ', error)
    }
}

makeCoffeeRoutine()
