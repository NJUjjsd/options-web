/**
 * Created by john on 2017/8/14.
 */
import React from 'react';
import { Layout } from 'antd';
import { connect } from 'dva';
import styles from './Footer.css';

const Footer = Layout.Footer;

function Foot() {
  return (
    <Footer className={styles.footer}>
      Options ©2017 Created by NJU JJSD
    </Footer>
  );
}

export default connect()(Foot);
