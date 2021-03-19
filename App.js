/**
 * Sample React Native App
 * https://github.com/facebook/react-native
 *
 * @format
 * @flow strict-local
 */

import React, { Component }  from 'react';
import {
  SafeAreaView,
  StyleSheet,
  ScrollView,
  View,
  TextInput ,
  Text,NativeModules,
  StatusBar,TouchableOpacity
} from 'react-native';

import {
  Header,
  LearnMoreLinks,
  Colors,
  DebugInstructions,
  ReloadInstructions,
} from 'react-native/Libraries/NewAppScreen';
import Communications from 'react-native-communications';
import SendSMS from 'react-native-sms'
class App extends Component {
  
  constructor(props) {
      super(props);
      this.state = {
        phone:"10086",
        msg:"cxhf"
      }
      that=this
  }
  componentWillMount(){

  }
  sendmsg(){
    // Communications.text('10086',"cxhf")
    Communications.textWithoutEncoding(this.state.phone, this.state.msg);//这种方法需要将内容encoding
  }
  someFunction() {
    SendSMS.send({
      body: this.state.msg,
      recipients: [this.state.phone],
      successTypes: ['sent', 'queued'],
      allowAndroidSendWithoutReadPermission: true
    }, (completed, cancelled, error) => {
  
      console.log('completed: ' + completed + ' ;cancelled: ' + cancelled + ';error: ' + error);
  
    });
  }
  someFunction2(){
    NativeModules.OpenNativeModule.sendmsg(this.state.phone,this.state.msg)

  }
  onChangeText(text){
    console.log(text)
    this.setState({
      phone:text
    })
  }
  onChangeText2(val){
    console.log(val)
    this.setState({
      msg:val
    })
  }
  render() {
    return (
      <>
        <StatusBar barStyle="dark-content" />
        <View style={styles.container}>
         
           <TextInput
            style={{ height: 40,width:'100%', borderColor: 'gray', borderWidth: 1 }}
            onChangeText={text => this.onChangeText(text)}
            value={this.state.phone}
          />
          <TextInput
            style={{ height: 40,width:'100%', borderColor: 'gray', borderWidth: 1 }}
            onChangeText={text => this.onChangeText2(text)}
            value={this.state.msg}
          />
          <TouchableOpacity onPress={() => this.sendmsg()}>
            <View style={styles.holder}>
              <Text style={styles.text}>发送短信{this.state.phone}</Text>
            </View>
          </TouchableOpacity>
          <TouchableOpacity onPress={() => this.someFunction()}>
            <View style={styles.holder}>
              <Text style={styles.text}>发送短信{this.state.phone}</Text>
            </View>
          </TouchableOpacity>
          
          <TouchableOpacity onPress={() => this.someFunction2()}>
            <View style={styles.holder}>
              <Text style={styles.text}>发送短信{this.state.phone}</Text>
            </View>
          </TouchableOpacity>
        </View>
      </>
    );
  }
}


const styles = StyleSheet.create({
  container: {
    margin:20 ,
    flex: 1,
    alignItems: 'center',
    backgroundColor: 'rgb(253,253,253)',
  },
  holder: {
    flex: 0.25,
    justifyContent: 'center',
  },
  text: {
    fontSize: 32,
  },
  scrollView: {
    backgroundColor: Colors.lighter,
  },
  engine: {
    position: 'absolute',
    right: 0,
  },
  body: {
    backgroundColor: Colors.white,
  },
  sectionContainer: {
    marginTop: 32,
    paddingHorizontal: 24,
  },
  sectionTitle: {
    fontSize: 24,
    fontWeight: '600',
    color: Colors.black,
  },
  sectionDescription: {
    marginTop: 8,
    fontSize: 18,
    fontWeight: '400',
    color: Colors.dark,
  },
  highlight: {
    fontWeight: '700',
  },
  footer: {
    color: Colors.dark,
    fontSize: 12,
    fontWeight: '600',
    padding: 4,
    paddingRight: 12,
    textAlign: 'right',
  },
});

export default App;
