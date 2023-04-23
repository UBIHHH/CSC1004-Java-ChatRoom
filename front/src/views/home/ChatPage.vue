<template>
    <div class="box-card">
        <br><strong>Welcome to CJ's anonymous chat room</strong><br/><br/>
        <textarea id="content" v-model="content" cols="80" rows="20" readonly="readonly" ></textarea><br>
        <el-input
            placeholder="Say something :)"
            v-model="message"
            type="text"
            clearable>
        </el-input>
        <el-button type="primary" @click="sendMsg()">Send</el-button>
        <br/><br/>
        nickname：<el-input size="medium" type="text" v-model="user"></el-input>
        <el-button type="success" @click="connect()">Join</el-button>
        <el-button type="danger" @click="disconnect()">Exit</el-button>
    </div>
</template>
<script>
import {Message} from "element-ui";

export default {
    name: 'ChatPage',
    data() {
        return {
            url: 'ws://'+ window.location.host + '/ChatRoom/',
            ws: null,
            user: '',
            message: '',
            content: '',
        }
    },
    created(){
    },
    methods: {
        async connect(){
            if(this.ws) {
                Message({
                    message: 'You are in the Chatroom',
                    type: 'warning'
                });
                return;
            }
            // eslint-disable-next-line no-unused-vars
            let url = this.url;
            let username = this.user;
            this.ws = new WebSocket('ws://127.0.0.1:8004/ChatRoom/' + username); //后端地址
            this.ws.onopen = this.websocketonopen;
            this.ws.onmessage =  this.websocketonmessage;
            //发生错误触发
            this.ws.onerror = function () {
                console.log("connection error")
            };
            //正常关闭触发
            this.ws.onclose = function () {
                console.log("connection closed");
            };
        },
        websocketonopen(){
            console.log("connected successfully");

        },
        websocketonmessage(value){
            console.log(value);
            this.content += (value.data + '\r\n') ;
        },
        disconnect(){
            this.closeWebSocket();
            this.$router.push('/login');
        },
        sendMsg(){
            if(!this.ws) {
                Message({
                    message: 'You are offline, please connect again',
                    type: 'error'
                });
                return;
            }
            if(this.ws.readyState === 1){
                this.ws.send(this.message);
                this.message = '';
            } else {
                Message({
                    message: 'Send failed',
                    type: 'error'
                })
            }
        },
        closeWebSocket(){
            if(this.ws) {
                this.ws.close();
                this.ws = null;
            }
        }
    }
}
</script>
<style>
.box-card{
    text-align: center;
    background-color: rgba(15, 161, 230, 0.35);
    margin: 0 auto;
    border: 1px solid #000;
    width: 600px;
    height: auto;
    background-image: url("bg1.jpg");
}


</style>