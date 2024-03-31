using System;
using System.Collections.Generic;
using System.ComponentModel;
using System.Data;
using System.Drawing;
using System.Text;
using System.Windows.Forms;
using System.Security.Policy;
using System.Security;
using System.Collections;

namespace CASRegister
{
    public partial class CASRegister : Form
    {
        public CASRegister()
        {
            InitializeComponent();
        }

        private void btnRegister_Click(object sender, EventArgs e)
        {
            if (CASRegister.addUrlCodeGroup(txtName.Text, txtURL.Text,
                txtDescription.Text))
            {
                lblStatus.Text = "성공적으로 추가되었습니다.";
            }
            else
            {
                lblStatus.Text = "이미 있는 이름이거나 내부 문제로 등록되지 않았습니다." +
                    "\n다시 시도해 주세요.";
            }
        }

        private static bool addUrlCodeGroup(string name, string url, string desc)
        {
            if (0 == name.Length|| 0 == url.Length || 0 == desc.Length)
            {
                MessageBox.Show("값을 모두 입력하지 않으셨습니다.");
                return false;
            }
            PolicyLevel policy = CASRegister.getMachineLevel();
            if (null == policy)
            {
                MessageBox.Show("Machine 정책을 찾을 수 없습니다.");
                return false;
            }
            CodeGroup rootCodeGroup = policy.RootCodeGroup;
            if (CASRegister.findCodeGroup(name, rootCodeGroup))
            {
                MessageBox.Show("이미 존재하는 코드 그룹입니다.");
                return false;
            }
            string registerURL = CASRegister.getRegisterURL(url);
            CASRegister.register(rootCodeGroup, name, registerURL, desc);
            return true;
        }

        private static void register(CodeGroup rootCodeGroup, string name, 
            string url, string desc)
        {
            UrlMembershipCondition membership = new UrlMembershipCondition(url);
            NamedPermissionSet permissionSet = new NamedPermissionSet("FullTrust");
            PolicyStatement policyStatement = new PolicyStatement(permissionSet);
            UnionCodeGroup cg = new UnionCodeGroup(membership, policyStatement);
            cg.Name = name;
            cg.Description = desc;
            rootCodeGroup.AddChild(cg);
            SecurityManager.SavePolicy();
        }

        private static string getRegisterURL(string url)
        {
            if ('/' != url[url.Length - 1])
            {
                url += '/';
            }
            url += "*";
            return url;
        }

        private static bool findCodeGroup(string name, CodeGroup rootCodeGroup)
        {
            foreach (CodeGroup cg in rootCodeGroup.Children)
            {
                if (name == cg.Name)
                {
                    return true;
                }
            }
            return false;
        }

        private static PolicyLevel getMachineLevel()
        {
            IEnumerator policies = SecurityManager.PolicyHierarchy();
            PolicyLevel policy = null;
            while (policies.MoveNext())
            {
                policy = policies.Current as PolicyLevel;
                if ("Machine" == policy.Label)
                {
                    return policy;
                }
            }
            return policy;
        }
    }
}