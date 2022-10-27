<script setup>

//let res

const callback = (response) => {
  // This callback will be triggered when the user selects or login to
  // his Google account from the popup
  console.log("Handle the response", response)
  //res = response

}

// defineExpose({
//   res,
//   })

</script>

<template>
  <GoogleLogin :callback="callback"/>
</template>