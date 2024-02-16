export const loginService = async (loginData) => {
    let isError=false;
    let responseData={}
    const response = await fetch("http://localhost:8080/authentication", {
        method: 'Post',
        headers: {
            'Content-Type': 'application/json',
        },
        body: JSON.stringify({ email: loginData.email, password: loginData.password })
    });

    if (!response.ok) {
        console.log("Error in Login");
        isError=true;
        
    }
    else{


    responseData = {
        userId: response.headers.get('userId'),
        token: response.headers.get('Authorization'),
        userName: response.headers.get('name'),
        userEmail: response.headers.get('email')
    }
}

    return {responseData,isError}
}

export const signUpService = async (signUpData) => {
    const repsonse = await fetch("http://localhost:8080/signup", {
        method: 'Post',
        headers: {
            'Content-Type': 'application/json',
        },
        body: JSON.stringify({ name: signUpData.name, email: signUpData.email, password: signUpData.password })
    });

    if (!repsonse.ok)
        console.log("Error is request");
    const jsonResponse = await repsonse.json();
    return jsonResponse;

}