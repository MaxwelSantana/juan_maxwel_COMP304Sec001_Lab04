/*
 * Copyright (C) 2021 The Android Open Source Project
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package com.lab1.juan_maxwel_comp304sec001_lab04

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.lab1.juan_maxwel_comp304sec001_lab04.database.schedule.AirSchedule
import com.lab1.juan_maxwel_comp304sec001_lab04.databinding.AirlineItemBinding
import java.text.SimpleDateFormat
import java.util.Date

class AirlineAdapter(
    private val onItemClicked: (AirSchedule) -> Unit
) : ListAdapter<AirSchedule, AirlineAdapter.AirlineViewHolder>(DiffCallback) {

    companion object {
        private val DiffCallback = object : DiffUtil.ItemCallback<AirSchedule>() {
            override fun areItemsTheSame(oldItem: AirSchedule, newItem: AirSchedule): Boolean {
                return oldItem.id == newItem.id
            }

            override fun areContentsTheSame(oldItem: AirSchedule, newItem: AirSchedule): Boolean {
                return oldItem == newItem
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): AirlineViewHolder {
        val viewHolder = AirlineViewHolder(
            AirlineItemBinding.inflate(
                LayoutInflater.from( parent.context),
                parent,
                false
            )
        )
        viewHolder.itemView.setOnClickListener {
            val position = viewHolder.adapterPosition
            onItemClicked(getItem(position))
        }
        return viewHolder
    }

    override fun onBindViewHolder(holder: AirlineViewHolder, position: Int) {
        holder.bind(getItem(position))
    }

    class AirlineViewHolder(
        private var binding: AirlineItemBinding
    ): RecyclerView.ViewHolder(binding.root) {
        @SuppressLint("SimpleDateFormat")
        fun bind(airSchedule: AirSchedule) {
            binding.airlineNameTextView.text = airSchedule.airlineName
            binding.arrivalTimeTextView.text = SimpleDateFormat(
                "h:mm a").format(Date(airSchedule.arrivalTime.toLong() * 1000)
            )
            binding.terminalTextView.text = airSchedule.terminal
        }
    }
}
