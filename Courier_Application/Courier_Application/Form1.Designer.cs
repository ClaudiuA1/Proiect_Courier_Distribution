namespace Courier_Application
{
    partial class Form1
    {
        /// <summary>
        /// Required designer variable.
        /// </summary>
        private System.ComponentModel.IContainer components = null;

        /// <summary>
        /// Clean up any resources being used.
        /// </summary>
        /// <param name="disposing">true if managed resources should be disposed; otherwise, false.</param>
        protected override void Dispose(bool disposing)
        {
            if (disposing && (components != null))
            {
                components.Dispose();
            }
            base.Dispose(disposing);
        }

        #region Windows Form Designer generated code

        /// <summary>
        /// Required method for Designer support - do not modify
        /// the contents of this method with the code editor.
        /// </summary>
        private void InitializeComponent()
        {
            this.components = new System.ComponentModel.Container();
            this.buttonAllCouriers = new System.Windows.Forms.Button();
            this.listBoxPackages = new System.Windows.Forms.ListBox();
            this.splitter1 = new System.Windows.Forms.Splitter();
            this.menuStrip1 = new System.Windows.Forms.MenuStrip();
            this.vizualizareToolStripMenuItem = new System.Windows.Forms.ToolStripMenuItem();
            this.gestionareToolStripMenuItem = new System.Windows.Forms.ToolStripMenuItem();
            this.buttonCouriers = new System.Windows.Forms.Button();
            this.panelGets = new System.Windows.Forms.Panel();
            this.buttonClear = new System.Windows.Forms.Button();
            this.buttonNoPending = new System.Windows.Forms.Button();
            this.buttonManagerDelivery = new System.Windows.Forms.Button();
            this.contextMenuStrip1 = new System.Windows.Forms.ContextMenuStrip(this.components);
            this.panelEmails = new System.Windows.Forms.Panel();
            this.buttonSend = new System.Windows.Forms.Button();
            this.label1 = new System.Windows.Forms.Label();
            this.textBoxSubject = new System.Windows.Forms.TextBox();
            this.listBoxNume = new System.Windows.Forms.ListBox();
            this.listBoxEmail = new System.Windows.Forms.ListBox();
            this.label2 = new System.Windows.Forms.Label();
            this.label3 = new System.Windows.Forms.Label();
            this.label4 = new System.Windows.Forms.Label();
            this.textBoxMessage = new System.Windows.Forms.TextBox();
            this.label5 = new System.Windows.Forms.Label();
            this.label6 = new System.Windows.Forms.Label();
            this.menuStrip1.SuspendLayout();
            this.panelGets.SuspendLayout();
            this.panelEmails.SuspendLayout();
            this.SuspendLayout();
            // 
            // buttonAllCouriers
            // 
            this.buttonAllCouriers.Location = new System.Drawing.Point(157, 236);
            this.buttonAllCouriers.Name = "buttonAllCouriers";
            this.buttonAllCouriers.Size = new System.Drawing.Size(123, 40);
            this.buttonAllCouriers.TabIndex = 0;
            this.buttonAllCouriers.Text = "All Packages";
            this.buttonAllCouriers.UseVisualStyleBackColor = true;
            this.buttonAllCouriers.Click += new System.EventHandler(this.button1_Click);
            // 
            // listBoxPackages
            // 
            this.listBoxPackages.FormattingEnabled = true;
            this.listBoxPackages.ItemHeight = 16;
            this.listBoxPackages.Location = new System.Drawing.Point(22, 63);
            this.listBoxPackages.Name = "listBoxPackages";
            this.listBoxPackages.Size = new System.Drawing.Size(836, 164);
            this.listBoxPackages.TabIndex = 2;
            // 
            // splitter1
            // 
            this.splitter1.Location = new System.Drawing.Point(0, 28);
            this.splitter1.Name = "splitter1";
            this.splitter1.Size = new System.Drawing.Size(3, 485);
            this.splitter1.TabIndex = 3;
            this.splitter1.TabStop = false;
            // 
            // menuStrip1
            // 
            this.menuStrip1.ImageScalingSize = new System.Drawing.Size(20, 20);
            this.menuStrip1.Items.AddRange(new System.Windows.Forms.ToolStripItem[] {
            this.vizualizareToolStripMenuItem,
            this.gestionareToolStripMenuItem});
            this.menuStrip1.Location = new System.Drawing.Point(0, 0);
            this.menuStrip1.Name = "menuStrip1";
            this.menuStrip1.Size = new System.Drawing.Size(973, 28);
            this.menuStrip1.TabIndex = 4;
            this.menuStrip1.Text = "menuStrip1";
            // 
            // vizualizareToolStripMenuItem
            // 
            this.vizualizareToolStripMenuItem.Name = "vizualizareToolStripMenuItem";
            this.vizualizareToolStripMenuItem.Size = new System.Drawing.Size(81, 24);
            this.vizualizareToolStripMenuItem.Text = "Visualize";
            this.vizualizareToolStripMenuItem.Click += new System.EventHandler(this.vizualizareToolStripMenuItem_Click);
            // 
            // gestionareToolStripMenuItem
            // 
            this.gestionareToolStripMenuItem.Name = "gestionareToolStripMenuItem";
            this.gestionareToolStripMenuItem.Size = new System.Drawing.Size(97, 24);
            this.gestionareToolStripMenuItem.Text = "Send Email";
            this.gestionareToolStripMenuItem.Click += new System.EventHandler(this.gestionareToolStripMenuItem_Click);
            // 
            // buttonCouriers
            // 
            this.buttonCouriers.Location = new System.Drawing.Point(22, 236);
            this.buttonCouriers.Name = "buttonCouriers";
            this.buttonCouriers.Size = new System.Drawing.Size(107, 40);
            this.buttonCouriers.TabIndex = 6;
            this.buttonCouriers.Text = "All couriers";
            this.buttonCouriers.UseVisualStyleBackColor = true;
            this.buttonCouriers.Click += new System.EventHandler(this.buttonBusyC_Click);
            // 
            // panelGets
            // 
            this.panelGets.Anchor = System.Windows.Forms.AnchorStyles.None;
            this.panelGets.BackColor = System.Drawing.SystemColors.ControlDark;
            this.panelGets.Controls.Add(this.label6);
            this.panelGets.Controls.Add(this.buttonManagerDelivery);
            this.panelGets.Controls.Add(this.buttonNoPending);
            this.panelGets.Controls.Add(this.buttonClear);
            this.panelGets.Controls.Add(this.buttonCouriers);
            this.panelGets.Controls.Add(this.listBoxPackages);
            this.panelGets.Controls.Add(this.buttonAllCouriers);
            this.panelGets.Location = new System.Drawing.Point(52, 67);
            this.panelGets.Name = "panelGets";
            this.panelGets.Size = new System.Drawing.Size(892, 382);
            this.panelGets.TabIndex = 8;
            // 
            // buttonClear
            // 
            this.buttonClear.BackColor = System.Drawing.Color.IndianRed;
            this.buttonClear.Location = new System.Drawing.Point(746, 236);
            this.buttonClear.Name = "buttonClear";
            this.buttonClear.Size = new System.Drawing.Size(112, 40);
            this.buttonClear.TabIndex = 7;
            this.buttonClear.Text = "Clear ListBox";
            this.buttonClear.UseVisualStyleBackColor = false;
            this.buttonClear.Click += new System.EventHandler(this.buttonClear_Click);
            // 
            // buttonNoPending
            // 
            this.buttonNoPending.Location = new System.Drawing.Point(311, 236);
            this.buttonNoPending.Name = "buttonNoPending";
            this.buttonNoPending.Size = new System.Drawing.Size(113, 41);
            this.buttonNoPending.TabIndex = 8;
            this.buttonNoPending.Text = "No Pending";
            this.buttonNoPending.UseVisualStyleBackColor = true;
            this.buttonNoPending.Click += new System.EventHandler(this.buttonNoPending_Click);
            // 
            // buttonManagerDelivery
            // 
            this.buttonManagerDelivery.BackColor = System.Drawing.SystemColors.Control;
            this.buttonManagerDelivery.Location = new System.Drawing.Point(462, 237);
            this.buttonManagerDelivery.Name = "buttonManagerDelivery";
            this.buttonManagerDelivery.Size = new System.Drawing.Size(197, 40);
            this.buttonManagerDelivery.TabIndex = 9;
            this.buttonManagerDelivery.Text = "Managers Delivered Packages";
            this.buttonManagerDelivery.UseVisualStyleBackColor = false;
            this.buttonManagerDelivery.Click += new System.EventHandler(this.buttonManagerDelivery_Click);
            // 
            // contextMenuStrip1
            // 
            this.contextMenuStrip1.ImageScalingSize = new System.Drawing.Size(20, 20);
            this.contextMenuStrip1.Name = "contextMenuStrip1";
            this.contextMenuStrip1.Size = new System.Drawing.Size(61, 4);
            // 
            // panelEmails
            // 
            this.panelEmails.Anchor = System.Windows.Forms.AnchorStyles.None;
            this.panelEmails.BackColor = System.Drawing.SystemColors.ControlDark;
            this.panelEmails.Controls.Add(this.label5);
            this.panelEmails.Controls.Add(this.textBoxMessage);
            this.panelEmails.Controls.Add(this.label4);
            this.panelEmails.Controls.Add(this.label3);
            this.panelEmails.Controls.Add(this.label2);
            this.panelEmails.Controls.Add(this.listBoxEmail);
            this.panelEmails.Controls.Add(this.listBoxNume);
            this.panelEmails.Controls.Add(this.textBoxSubject);
            this.panelEmails.Controls.Add(this.label1);
            this.panelEmails.Controls.Add(this.buttonSend);
            this.panelEmails.Location = new System.Drawing.Point(118, 56);
            this.panelEmails.Name = "panelEmails";
            this.panelEmails.Size = new System.Drawing.Size(775, 413);
            this.panelEmails.TabIndex = 11;
            // 
            // buttonSend
            // 
            this.buttonSend.Location = new System.Drawing.Point(271, 294);
            this.buttonSend.Name = "buttonSend";
            this.buttonSend.Size = new System.Drawing.Size(156, 39);
            this.buttonSend.TabIndex = 1;
            this.buttonSend.Text = "Send";
            this.buttonSend.UseVisualStyleBackColor = true;
            this.buttonSend.Click += new System.EventHandler(this.buttonSend_Click);
            // 
            // label1
            // 
            this.label1.AutoSize = true;
            this.label1.Font = new System.Drawing.Font("Microsoft Sans Serif", 13.2F);
            this.label1.Location = new System.Drawing.Point(3, 13);
            this.label1.Name = "label1";
            this.label1.Size = new System.Drawing.Size(358, 52);
            this.label1.TabIndex = 2;
            this.label1.Text = "Select the emails u want \r\n                         to send the email to:";
            // 
            // textBoxSubject
            // 
            this.textBoxSubject.Font = new System.Drawing.Font("Microsoft Sans Serif", 12F, System.Drawing.FontStyle.Regular, System.Drawing.GraphicsUnit.Point, ((byte)(0)));
            this.textBoxSubject.Location = new System.Drawing.Point(517, 95);
            this.textBoxSubject.Name = "textBoxSubject";
            this.textBoxSubject.Size = new System.Drawing.Size(190, 30);
            this.textBoxSubject.TabIndex = 3;
            // 
            // listBoxNume
            // 
            this.listBoxNume.FormattingEnabled = true;
            this.listBoxNume.ItemHeight = 16;
            this.listBoxNume.Location = new System.Drawing.Point(46, 95);
            this.listBoxNume.Name = "listBoxNume";
            this.listBoxNume.Size = new System.Drawing.Size(120, 132);
            this.listBoxNume.TabIndex = 4;
            // 
            // listBoxEmail
            // 
            this.listBoxEmail.FormattingEnabled = true;
            this.listBoxEmail.ItemHeight = 16;
            this.listBoxEmail.Location = new System.Drawing.Point(183, 95);
            this.listBoxEmail.Name = "listBoxEmail";
            this.listBoxEmail.SelectionMode = System.Windows.Forms.SelectionMode.MultiSimple;
            this.listBoxEmail.Size = new System.Drawing.Size(286, 132);
            this.listBoxEmail.TabIndex = 5;
            // 
            // label2
            // 
            this.label2.AutoSize = true;
            this.label2.Font = new System.Drawing.Font("Microsoft Sans Serif", 10F);
            this.label2.Location = new System.Drawing.Point(55, 73);
            this.label2.Name = "label2";
            this.label2.Size = new System.Drawing.Size(53, 20);
            this.label2.TabIndex = 6;
            this.label2.Text = "Name";
            // 
            // label3
            // 
            this.label3.AutoSize = true;
            this.label3.Font = new System.Drawing.Font("Microsoft Sans Serif", 10F);
            this.label3.Location = new System.Drawing.Point(190, 73);
            this.label3.Name = "label3";
            this.label3.Size = new System.Drawing.Size(51, 20);
            this.label3.TabIndex = 7;
            this.label3.Text = "Email";
            // 
            // label4
            // 
            this.label4.AutoSize = true;
            this.label4.Font = new System.Drawing.Font("Microsoft Sans Serif", 10F);
            this.label4.Location = new System.Drawing.Point(514, 76);
            this.label4.Name = "label4";
            this.label4.Size = new System.Drawing.Size(192, 20);
            this.label4.TabIndex = 8;
            this.label4.Text = "Write the mail\'s Subject:";
            // 
            // textBoxMessage
            // 
            this.textBoxMessage.Font = new System.Drawing.Font("Microsoft Sans Serif", 7.8F);
            this.textBoxMessage.Location = new System.Drawing.Point(517, 165);
            this.textBoxMessage.Multiline = true;
            this.textBoxMessage.Name = "textBoxMessage";
            this.textBoxMessage.Size = new System.Drawing.Size(190, 91);
            this.textBoxMessage.TabIndex = 9;
            // 
            // label5
            // 
            this.label5.AutoSize = true;
            this.label5.Font = new System.Drawing.Font("Microsoft Sans Serif", 10F);
            this.label5.Location = new System.Drawing.Point(514, 146);
            this.label5.Name = "label5";
            this.label5.Size = new System.Drawing.Size(204, 20);
            this.label5.TabIndex = 10;
            this.label5.Text = "Write the mail\'s Message:";
            // 
            // label6
            // 
            this.label6.AutoSize = true;
            this.label6.Font = new System.Drawing.Font("Microsoft Sans Serif", 13.2F);
            this.label6.Location = new System.Drawing.Point(32, 16);
            this.label6.Name = "label6";
            this.label6.Size = new System.Drawing.Size(326, 26);
            this.label6.TabIndex = 12;
            this.label6.Text = "View the couriers and packages:";
            // 
            // Form1
            // 
            this.AutoScaleDimensions = new System.Drawing.SizeF(8F, 16F);
            this.AutoScaleMode = System.Windows.Forms.AutoScaleMode.Font;
            this.ClientSize = new System.Drawing.Size(973, 513);
            this.Controls.Add(this.panelEmails);
            this.Controls.Add(this.panelGets);
            this.Controls.Add(this.splitter1);
            this.Controls.Add(this.menuStrip1);
            this.MainMenuStrip = this.menuStrip1;
            this.Name = "Form1";
            this.Text = "Form1";
            this.menuStrip1.ResumeLayout(false);
            this.menuStrip1.PerformLayout();
            this.panelGets.ResumeLayout(false);
            this.panelGets.PerformLayout();
            this.panelEmails.ResumeLayout(false);
            this.panelEmails.PerformLayout();
            this.ResumeLayout(false);
            this.PerformLayout();

        }

        #endregion

        private System.Windows.Forms.Button buttonAllCouriers;
        private System.Windows.Forms.ListBox listBoxPackages;
        private System.Windows.Forms.Splitter splitter1;
        private System.Windows.Forms.MenuStrip menuStrip1;
        private System.Windows.Forms.ToolStripMenuItem vizualizareToolStripMenuItem;
        private System.Windows.Forms.ToolStripMenuItem gestionareToolStripMenuItem;
        private System.Windows.Forms.Button buttonCouriers;
        private System.Windows.Forms.Panel panelGets;
        private System.Windows.Forms.Button buttonClear;
        private System.Windows.Forms.Button buttonManagerDelivery;
        private System.Windows.Forms.Button buttonNoPending;
        private System.Windows.Forms.ContextMenuStrip contextMenuStrip1;
        private System.Windows.Forms.Panel panelEmails;
        private System.Windows.Forms.TextBox textBoxSubject;
        private System.Windows.Forms.Label label1;
        private System.Windows.Forms.Button buttonSend;
        private System.Windows.Forms.Label label3;
        private System.Windows.Forms.Label label2;
        private System.Windows.Forms.ListBox listBoxEmail;
        private System.Windows.Forms.ListBox listBoxNume;
        private System.Windows.Forms.Label label4;
        private System.Windows.Forms.Label label5;
        private System.Windows.Forms.TextBox textBoxMessage;
        private System.Windows.Forms.Label label6;
    }
}

